package com.xiaolintt.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题）
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@TableName("question_lib")
@Data
public class QuestionLib implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 面试题（文字内容）
     */
    private String question;

    /**
     * 参考答案
     */
    private String referenceAnswer;

    /**
     * 面试数字人对应的地址
     */
    private String aiSrc;

    /**
     * 分配的数字人面试官id，每个职位需要对应的面试官来进行面试
     */
    private String interviewerId;

    /**
     * 1：启用本题
0：关闭本题
     */
    private Integer isOn;

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;


}
