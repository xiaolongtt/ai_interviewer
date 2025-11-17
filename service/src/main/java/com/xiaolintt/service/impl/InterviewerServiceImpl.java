package com.xiaolintt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaolintt.Bo.InterviewerBo;
import com.xiaolintt.mapper.InterviewerMapper;
import com.xiaolintt.po.Interviewer;
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
        //TODO 检查是否有正在进行的面试和面试官关联的题目等
        interviewerMapper.deleteById(interviewerId);
    }

    @Override
    public List<Interviewer> list() {
        LambdaQueryWrapper<Interviewer> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Interviewer::getUpdatedTime);
        return interviewerMapper.selectList(queryWrapper);
    }
}
