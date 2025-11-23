package com.xiaolintt.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 23:53
 * @注释
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerBO {

    private String id;
    private String question;
    private String referenceAnswer;
    private String aiSrc;
    private String interviewerId;
    private String answerContent;
}