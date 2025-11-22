package com.xiaolintt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.xiaolintt.Bo.QuestionLibBo;
import com.xiaolintt.enums.ResponseStatusEnum;
import com.xiaolintt.exception.MyCustomException;
import com.xiaolintt.mapper.CandidateMapper;
import com.xiaolintt.mapper.JobMapper;
import com.xiaolintt.mapper.QuestionLibMapper;
import com.xiaolintt.po.Candidate;
import com.xiaolintt.po.Job;
import com.xiaolintt.po.QuestionLib;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.IQuestionLibService;
import com.xiaolintt.vo.InitQuestionsVO;
import com.xiaolintt.vo.QuestionLibVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:06
 * @注释
 */
@Service
public class QuestionLibServiceImpl implements IQuestionLibService {
    @Resource
    private QuestionLibMapper questionLibMapper;

    @Resource
    private CandidateMapper candidateMapper;

    @Resource
    private JobMapper jobMapper;

    @Override
    public void createOrUpdate(QuestionLibBo questionLibBo) {
        String id = questionLibBo.getId();
        QuestionLib questionLib = new QuestionLib();
        BeanUtils.copyProperties(questionLibBo, questionLib);
        questionLib.setIsOn(1);
        questionLib.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(id)){
            // 新增
            questionLib.setCreateTime(LocalDateTime.now());
            questionLibMapper.insert(questionLib);
        }else{
            // 更新
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public PageGraceResult list(String aiName, String question, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<QuestionLibVO> list = questionLibMapper.selectQuestionByInterviewer(question, aiName);
        long records = questionLibMapper.selectCount(null);
        int totalPages = ((int) records + pageSize - 1) / pageSize;
        return new PageGraceResult(page, totalPages, (int) records, list);
    }

    @Override
    public void hide(String questionLibId) {
        QuestionLib questionLib = questionLibMapper.selectById(questionLibId);
        if(questionLib != null){
            questionLib.setIsOn(0);
            questionLib.setUpdatedTime(LocalDateTime.now());
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public void show(String questionLibId) {
        QuestionLib questionLib = questionLibMapper.selectById(questionLibId);
        if(questionLib != null){
            questionLib.setIsOn(1);
            questionLib.setUpdatedTime(LocalDateTime.now());
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public void delete(String questionLibId) {
        questionLibMapper.deleteById(questionLibId);
    }

    @Override
    public List<InitQuestionsVO> prepareQuestion(String candidateId) {
        //先获取负责面试的面试官
        Candidate candidate = candidateMapper.selectById(candidateId);
        if(StringUtils.isBlank(candidate.getJobId())){
            throw new MyCustomException(ResponseStatusEnum.USER_STATUS_ERROR);
        }
        //根据jobId查询职位
        List<Job> jobs = jobMapper.selectList(new LambdaQueryWrapper<Job>().eq(Job::getId, candidate.getJobId()).last("limit 1"));
        if(jobs.isEmpty()){
            throw new MyCustomException(ResponseStatusEnum.USER_STATUS_ERROR);
        }
        Job job = jobs.get(0);
        //获取到对应的面试官
        String interviewerId = job.getInterviewerId();
        //获取对应面试官的题目列表
        List<QuestionLib> questionLibs = questionLibMapper.selectList(new LambdaQueryWrapper<QuestionLib>().eq(QuestionLib::getInterviewerId, interviewerId));
        if(questionLibs.isEmpty()) {
            throw new MyCustomException(ResponseStatusEnum.USER_STATUS_ERROR);
        }
        //在题库中随机抽取10道题目
        int size =questionLibs.size();
        //生成10个小于size的不重复的随机数
        Random random = new Random();
        Set<Integer> randomSet = new HashSet<>();
        while(randomSet.size() < 10){
            randomSet.add(random.nextInt(size));
        }
        List<InitQuestionsVO> initQuestionsVOS = new ArrayList<>();
        for (Integer integer : randomSet) {
            InitQuestionsVO initQuestionsVO = new InitQuestionsVO();
            BeanUtils.copyProperties(questionLibs.get(integer), initQuestionsVO);
            initQuestionsVO.setQuestionLibId(questionLibs.get(integer).getId());
            initQuestionsVOS.add(initQuestionsVO);
        }
        return initQuestionsVOS;
    }
}
