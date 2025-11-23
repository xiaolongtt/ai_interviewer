package com.xiaolintt.service.impl;

import ai.z.openapi.ZhipuAiClient;
import ai.z.openapi.service.model.*;
import com.github.pagehelper.PageHelper;
import com.xiaolintt.Bo.AnswerBO;
import com.xiaolintt.Bo.SubmitAnswerBO;
import com.xiaolintt.ZhipuAi.ZhipuAiConfig;
import com.xiaolintt.enums.ChatGlmModelEnum;
import com.xiaolintt.mapper.InterviewRecordMapper;
import com.xiaolintt.mapper.JobMapper;
import com.xiaolintt.po.InterviewRecord;
import com.xiaolintt.po.Job;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.IInterviewRecordService;
import com.xiaolintt.vo.CandidateVO;
import com.xiaolintt.vo.InterviewRecordVO;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:07
 * @注释
 */
@Service
public class InterviewRecordServiceImpl implements IInterviewRecordService {

    @Resource
    private ZhipuAiConfig zhipuAiConfig;

    @Resource
    private JobMapper jobMapper;

    @Resource
    private InterviewRecordMapper interviewRecordMapper;

    @Override
    @Async
    public void display(SubmitAnswerBO submitAnswerBO) {
        //获取到面试者回答的问题答案列表
        List<AnswerBO> questionAnswerList = submitAnswerBO.getQuestionAnswerList();
        String jobId = submitAnswerBO.getJobId();
        Job job = jobMapper.selectById(jobId);
        String jobName = job.getJobName();
        String prompt = job.getPrompt();
        List<ChatMessage> messageList =new ArrayList<>();
        messageList.add(new ChatMessage(ChatMessageRole.USER.value(),"你好，你是"+jobName+"这个职位的面试官，现在需要你对面试者回答的问题进行打分和评价"+prompt));
        StringBuilder AnswerContent = new StringBuilder();
        for (AnswerBO answerBO : questionAnswerList) {
            String question = answerBO.getQuestion();
            String answerContent = answerBO.getAnswerContent();
            String referenceAnswer = answerBO.getReferenceAnswer();
            AnswerContent.append(String.format("问题：%s;\n回答：%s;\n", question, answerContent));
            // 构建聊天消息
            ChatMessage message = ChatMessage.builder()
                    .role(ChatMessageRole.USER.value())
                    .content(String.format("问题：%s;\n用户回答：%s;\n参考答案：%s;\n请结合题目的参考答案来判断用户回答是否正确并且进行打分(0分-10分)和评价", question, answerContent, referenceAnswer))
                    .build();
            messageList.add(message);
        }
        ChatMessage message = ChatMessage.builder().role(ChatMessageRole.USER.value()).content("对上面的全部题目评价和打分结束后,得出一个总分,并给出一个评价").build();
        messageList.add(message);
        ZhipuAiClient zhipuAiClient = zhipuAiConfig.createZhipuAiClient();
        // 创建聊天完成请求
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
                .model(ChatGlmModelEnum.GLM_4_6.getModel())
                .messages(messageList)
                .stream(true)
                .build();
        // 发送请求
        ChatCompletionResponse response = zhipuAiClient.chat().createChatCompletion(request);
        StringBuilder totalResult = new StringBuilder();
        if (response.isSuccess() && response.getFlowable() != null) {
            response.getFlowable().subscribe(
                    data -> {
                        // 处理流式数据块
                        if (data.getChoices() != null && !data.getChoices().isEmpty()) {
                            Delta delta  = data.getChoices().get(0).getDelta();
                            // 提取并显示内容
                            String content = extractContent(delta);
                            //String reasoningContent = extractReasoningContent(delta);
                            if (content != null && !content.isEmpty()) {
                                System.out.print(content);
                                totalResult.append(content);
                                // 在句子结束时换行
                                if (content.matches(".*[。！？]$")) {
                                    System.out.println();
                                    totalResult.append("\n");
                                }
                            }
                        }
                    },
                    error -> System.err.println("\n 流式错误: " + error.getMessage()),
                    () -> System.out.println("\n 流式完成")
            );
        }
        //完成以后要保存面试记录
        InterviewRecord interviewRecord=new InterviewRecord();
        interviewRecord.setCandidateId(submitAnswerBO.getCandidateId());
        interviewRecord.setJobName(jobName);
        interviewRecord.setAnswerContent(AnswerContent.toString());
        interviewRecord.setTakeTime(submitAnswerBO.getTotalSeconds());
        interviewRecord.setResult(totalResult.toString());
        interviewRecord.setCreateTime(LocalDateTime.now());
        interviewRecord.setUpdatedTime(LocalDateTime.now());
        interviewRecordMapper.insert(interviewRecord);
    }

    @Override
    public PageGraceResult list(String realName, String mobile, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<InterviewRecordVO> candidateList = interviewRecordMapper.queryInterviewRecordLibList(realName, mobile);
        long records =interviewRecordMapper.selectCount(null);
        int totalPages = ((int) records + pageSize - 1) / pageSize;
        return new PageGraceResult(page, totalPages, (int) records, candidateList);
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
//    private static String extractReasoningContent(Delta delta) {
//        if (delta == null) return null;
//        try {
//            return delta.getReasoningContent();
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
