package com.xiaolintt.Bo;

import lombok.Data;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 19:03
 * @注释
 */
@Data
public class QuestionLibBo {
    private String id;
    private String question;

    private String referenceAnswer;

    /**
     * 面试数字人对应的地址
     */
    private String aiSrc;

    /**
     * 分配的数字人面试官id，每个职位需要对应的面试官来进行面试
     */
    private String interviewerId;

}
