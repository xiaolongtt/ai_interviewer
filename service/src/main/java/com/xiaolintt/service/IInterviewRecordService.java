package com.xiaolintt.service;

import com.xiaolintt.Bo.SubmitAnswerBO;
import com.xiaolintt.result.PageGraceResult;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:05
 * @注释
 */
public interface IInterviewRecordService {
    /**
     * 生成面试结果，调用ChatGLM模型对提交的面试答案进行分析打分，并且将结果保存到数据库中
     * @param submitAnswerBO
     */
    void display(SubmitAnswerBO submitAnswerBO);

    PageGraceResult list(String realName, String mobile, Integer page, Integer pageSize);
}
