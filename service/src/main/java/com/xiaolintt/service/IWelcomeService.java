package com.xiaolintt.service;

import com.xiaolintt.Bo.VerifySMSBO;
import com.xiaolintt.vo.CandidateVO;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/21 21:50
 * @注释
 */
public interface IWelcomeService {
    String getSMSCode(String mobile);

    CandidateVO verify(VerifySMSBO verifySMSBO);
}
