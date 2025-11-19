package com.xiaolintt.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaolintt.Bo.CandidateBO;
import com.xiaolintt.mapper.CandidateMapper;
import com.xiaolintt.po.Candidate;
import com.xiaolintt.po.Job;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.ICandidateService;
import com.xiaolintt.vo.CandidateVO;
import com.xiaolintt.vo.JobVo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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
public class CandidateServiceImpl implements ICandidateService {

    @Resource
    private CandidateMapper candidateMapper;
    @Override
    public PageGraceResult queryList(String realName, String mobile, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<CandidateVO> candidateList = candidateMapper.queryCandidateLibList(realName, mobile);
        long records =candidateMapper.selectCount(null);
        int totalPages = ((int) records + pageSize - 1) / pageSize;
        return new PageGraceResult(page, totalPages, (int) records, candidateList);
    }

    @Override
    public void createOrUpdate(CandidateBO candidateBO) {
        String id = candidateBO.getId();
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(candidateBO, candidate);
        candidate.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(id)){
            candidate.setCreatedTime(LocalDateTime.now());
            candidateMapper.insert(candidate);
        }else{
            candidateMapper.updateById(candidate);
        }
    }

    @Override
    public Candidate getDetail(String candidateId) {
        return candidateMapper.selectById(candidateId);
    }

    @Override
    public void delete(String candidateId) {
        candidateMapper.deleteById(candidateId);
    }
}
