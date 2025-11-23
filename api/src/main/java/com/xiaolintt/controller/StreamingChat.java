package com.xiaolintt.controller;

import ai.z.openapi.ZhipuAiClient;
import ai.z.openapi.service.model.*;
import ai.z.openapi.core.Constants;
import com.xiaolintt.Bo.AnswerBO;
import com.xiaolintt.enums.ChatGlmModelEnum;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamingChat {

    public static void main(String[] args) {
        ZhipuAiClient client = ZhipuAiClient.builder()
            .apiKey("7c392b36a9164eb7a5cbe9896ce0a0b3.Br4FGriy08I6xKBI")
            .build();
        List<AnswerBO> questionAnswerList=new ArrayList<>();
        questionAnswerList.add(new AnswerBO("1","spring的几大特性","spring的几大特性有：依赖注入（DI）、面向切面编程（AOP）、事务管理、声明式事务、IOC容器、事件驱动模型等","zhipuai","1","spring有三大特性"));
        questionAnswerList.add(new AnswerBO("1","java中的arraylist和linkedlist的区别","arraylist是基于数组实现的，linkedlist是基于链表实现的","zhipuai","1","arraylist和linkedlist的区别不太了解"));
        List<ChatMessage> messageList =new ArrayList<>();
        messageList.add(new ChatMessage(ChatMessageRole.USER.value(),"你好，你是java后端这个职位的面试官，现在需要你对面试者回答的问题进行打分和评价"));
        for (AnswerBO answerBO : questionAnswerList) {
            String question = answerBO.getQuestion();
            String answerContent = answerBO.getAnswerContent();
            String referenceAnswer = answerBO.getReferenceAnswer();
            // 构建聊天消息
            ChatMessage message = ChatMessage.builder()
                    .role(ChatMessageRole.USER.value())
                    .content(String.format("问题：%s;\n用户回答：%s;\n参考答案：%s;\n请结合题目的参考答案来判断用户回答是否正确并且进行打分(0分-10分)和评价", question, answerContent, referenceAnswer))
                    .build();
            messageList.add(message);
        }
        ChatMessage message = ChatMessage.builder().role(ChatMessageRole.USER.value()).content("对上面的全部题目评价和打分结束后,得出一个总分,并给出一个评价，用html标签展示").build();
        messageList.add(message);
        System.out.println(messageList);
        // 创建流式聊天请求
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
            .model(ChatGlmModelEnum.GLM_4_6.getModel())
            .messages(messageList)
            .stream(true)
            .build();
        
        // 处理流式响应
        ChatCompletionResponse response = client.chat().createChatCompletion(request);
        
        if (response.isSuccess() && response.getFlowable() != null) {
            response.getFlowable().subscribe(
                data -> {
                    // 处理流式数据块
                    if (data.getChoices() != null && !data.getChoices().isEmpty()) {
                        Delta delta  = data.getChoices().get(0).getDelta();
                        // 提取并显示内容
                        String content = extractContent(delta);
                        String reasoningContent = extractReasoningContent(delta);

                        if (content != null && !content.isEmpty()) {
                            System.out.print(content);

                            // 在句子结束时换行
                            if (content.matches(".*[。！？]$")) {
                                System.out.println();
                            }
                        }

//                        if (reasoningContent != null && !reasoningContent.isEmpty()) {
//                            System.out.print("【思考】" + reasoningContent);
//                        }
                    }
                },
                error -> System.err.println("\n 流式错误: " + error.getMessage()),
                () -> System.out.println("\n 流式完成")
            );
        }
    }
    /**
     * 提取Delta中的content内容
     */
    private static String extractContent(Delta delta) {
        if (delta == null) return null;
        try {
            return delta.getContent();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提取Delta中的reasoningContent内容
     */
    private static String extractReasoningContent(Delta delta) {
        if (delta == null) return null;
        try {
            return delta.getReasoningContent();
        } catch (Exception e) {
            return null;
        }
    }
}