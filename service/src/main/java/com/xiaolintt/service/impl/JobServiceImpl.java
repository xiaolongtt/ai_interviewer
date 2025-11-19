package com.xiaolintt.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaolintt.Bo.JobBo;
import com.xiaolintt.mapper.JobMapper;
import com.xiaolintt.po.Job;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.IJobService;
import com.xiaolintt.vo.JobVo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:07
 * @注释
 */
@Service
public class JobServiceImpl implements IJobService {

    @Resource
    private JobMapper jobMapper;
    @Override
    public void createOrUpdate(JobBo jobBo) {
        String id = jobBo.getId();
        Job job = new Job();
        BeanUtils.copyProperties(jobBo, job);
        job.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(id)){
            job.setCreateTime(LocalDateTime.now());
            jobMapper.insert(job);
        }else{
            jobMapper.updateById(job);
        }
    }

    @Override
    public PageGraceResult list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<JobVo> jobList = jobMapper.selectListByInterviewer();
        long records =jobMapper.selectCount(null);
        int totalPages = ((int) records + pageSize - 1) / pageSize;
        return new PageGraceResult(page, totalPages, (int) records, jobList);
    }

    @Override
    public void delete(String jobId) {
        jobMapper.deleteById(jobId);
    }

    @Override
    public JobVo getDetail(String jobId) {
        return jobMapper.selectJobVoById(jobId);
    }

    @Override
    public List<Map<String,String>> getNameList() {
        return jobMapper.selectList(null).stream().map(Job->Map.of("jobId",Job.getId(),"jobName",Job.getJobName())).toList();
    }
}
