package com.xiaolintt.service;

import com.xiaolintt.Bo.CandidateBO;
import com.xiaolintt.po.Candidate;
import com.xiaolintt.result.PageGraceResult;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:05
 * @注释
 */
public interface ICandidateService {
    PageGraceResult queryList(String realName, String mobile, Integer page, Integer pageSize);

    void createOrUpdate(CandidateBO candidateBO);

    Candidate getDetail(String candidateId);

    void delete(String candidateId);
}
