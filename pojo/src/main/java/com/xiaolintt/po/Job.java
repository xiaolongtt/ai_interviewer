package com.xiaolintt.po;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Data
public class Job implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;

}
