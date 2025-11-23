package com.xiaolintt.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 23:53
 * @注释
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAnswerBO {

    private String candidateId;
    private String jobId;
    private List<AnswerBO> questionAnswerList;
    private Integer totalSeconds;
}
