package com.xiaolintt.service;

import com.xiaolintt.Bo.InterviewerBo;
import com.xiaolintt.po.Interviewer;

import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:05
 * @注释
 */
public interface IInterviewerService {
    void createOrUpdate(InterviewerBo interviewerBo);


    void deleteInterviewer(String interviewerId);

    List<Interviewer> list();
}
