package com.xiaolintt.po;

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
public class Interviewer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 数字人面试官的名称
     */
    private String aiName;

    /**
     * 数字人形象图照片
     */
    private String image;

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;

}
