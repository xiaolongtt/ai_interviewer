package com.xiaolintt.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 面试记录表
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@TableName("interview_record")
@Data
public class InterviewRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 应聘者id
     */
    private String candidateId;

    /**
     * 职位名称，快照名称，原字段可能会更改
     */
    private String jobName;

    /**
     * 应聘者的回答内容
     */
    private String answerContent;

    /**
     * 整个面试所花费的总时间，单位：秒
     */
    private Integer takeTime;

    /**
     * 面试结果详情
     */
    private String result;

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;

}
