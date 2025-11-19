package com.xiaolintt.Bo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/18 22:25
 * @注释
 */
@Data
public class CandidateBO {
    private String id;
    private String realName;
    private Integer sex;
    private LocalDate birthday;
    private String identityNum;
    private String mobile;
    private String email;
    private String jobId;
}
