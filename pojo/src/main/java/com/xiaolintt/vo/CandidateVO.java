package com.xiaolintt.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;

import java.time.LocalDate;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/18 22:44
 * @注释
 */
@Data
public class CandidateVO {
    private String candidateId;
    private String realName;
    private String identityNum;
    private String mobile;
    private Integer sex;
    private String email;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;
    private String jobId;
    private String jobName;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdTime;
    private String userToken;
}
