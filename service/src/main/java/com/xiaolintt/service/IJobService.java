package com.xiaolintt.service;

import com.xiaolintt.Bo.JobBo;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.vo.JobVo;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:06
 * @注释
 */
public interface IJobService {
    void createOrUpdate(JobBo jobBo);

    PageGraceResult list(Integer page, Integer pageSize);

    void delete(String jobId);

    JobVo getDetail(String jobId);
}
