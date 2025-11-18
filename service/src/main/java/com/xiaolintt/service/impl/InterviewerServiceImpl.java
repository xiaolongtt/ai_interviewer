package com.xiaolintt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaolintt.Bo.InterviewerBo;
import com.xiaolintt.enums.ResponseStatusEnum;
import com.xiaolintt.exception.MyCustomException;
import com.xiaolintt.mapper.InterviewerMapper;
import com.xiaolintt.mapper.JobMapper;
import com.xiaolintt.mapper.QuestionLibMapper;
import com.xiaolintt.po.Interviewer;
import com.xiaolintt.po.Job;
import com.xiaolintt.po.QuestionLib;
import com.xiaolintt.service.IInterviewerService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:07
 * @注释
 */
@Service
public class InterviewerServiceImpl implements IInterviewerService {

    @Resource
    private InterviewerMapper interviewerMapper;

    @Resource
    private JobMapper jobMapper;

    @Resource
    private QuestionLibMapper questionLibMapper;

    @Override
    public void createOrUpdate(InterviewerBo interviewerBo) {
        //先获取到用户id
        String id = interviewerBo.getId();
        Interviewer interviewer=new Interviewer();
        BeanUtils.copyProperties(interviewerBo,interviewer);
        interviewer.setUpdatedTime(LocalDateTime.now());
        if(id==null||id.isEmpty()){
            //创建用户
            //插入到数据库
            interviewer.setCreateTime(LocalDateTime.now());
            interviewerMapper.insert(interviewer);
        }else{
            //更新用户
            //更新到数据库
            interviewerMapper.updateById(interviewer);
        }
    }

    @Override
    public void deleteInterviewer(String interviewerId) {
        //检查是否有正在进行的面试和面试官关联的题目等
        LambdaQueryWrapper<Job> lambdaQueryWrapper=new LambdaQueryWrapper<Job>();
        lambdaQueryWrapper.eq(Job::getInterviewerId,interviewerId);
        List<Job> jobs = jobMapper.selectList(lambdaQueryWrapper);
        if(!jobs.isEmpty()){
            //说明有正在进行的面试或面试官关联的题目等
            throw new MyCustomException(ResponseStatusEnum.CAN_NOT_DELETE_INTERVIEWER);
        }
        LambdaQueryWrapper<QuestionLib> questionLibLambdaQueryWrapper=new LambdaQueryWrapper<>();
        questionLibLambdaQueryWrapper.eq(QuestionLib::getInterviewerId,interviewerId);
        List<QuestionLib> questionLibs = questionLibMapper.selectList(questionLibLambdaQueryWrapper);
        if(!questionLibs.isEmpty()){
            //说明有题目关联
            throw new MyCustomException(ResponseStatusEnum.CAN_NOT_DELETE_INTERVIEWER);
        }
        interviewerMapper.deleteById(interviewerId);
    }

    @Override
    public List<Interviewer> list() {
        LambdaQueryWrapper<Interviewer> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Interviewer::getUpdatedTime);
        return interviewerMapper.selectList(queryWrapper);
    }
}
