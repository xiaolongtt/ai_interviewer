package com.xiaolintt.vo;

import lombok.Data;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 23:53
 * @注释
 */
@Data
public class JobVo {
    private String jobId;
    private String jobName;
    private String jobDesc;
    private Integer status;
    private String prompt;
    private String interviewerId;
    private String interviewerName;
    private String createTime;
    private String updateTime;
}
