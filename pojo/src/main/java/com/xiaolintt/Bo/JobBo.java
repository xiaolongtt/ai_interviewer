package com.xiaolintt.Bo;

import lombok.Data;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 23:40
 * @注释
 */
@Data
public class JobBo {
    private String id;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 职位描述
     */
    private String jobDesc;

    /**
     * 1：职位开启
     2：职位关闭
     */
    private Integer status;

    /**
     * 分配的数字人面试官id，每个职位需要对应的面试官来进行面试
     */
    private String interviewerId;

    /**
     * 该职位的面试结果最终发给ChatGLM的前缀提示词
     */
    private String prompt;
}
