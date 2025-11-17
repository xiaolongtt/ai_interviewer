package com.xiaolintt.Bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数字人面试官表
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Data
public class InterviewerBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 数字人面试官的名称
     */
    @NotBlank(message = "数字人面试官的名称不能为空")
    private String aiName;

    /**
     * 数字人形象图照片
     */
    @NotBlank(message = "数字人形象图照片不能为空")
    private String image;
}
