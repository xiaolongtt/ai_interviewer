package com.xiaolintt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 19:22
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionLibVO {
    private String questionLibId;
    private String question;
    private String referenceAnswer;
    private String aiSrc;
    private String interviewerId;
    private Integer isOn;
    private String interviewerName;
    private LocalDateTime createTime;
    private LocalDateTime updatedTime;
}
