package com.xiaolintt.po;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 运营管理平台的admin级别用户
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Data
public class Admin implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 人脸图片信息
     */
    private String face;

    /**
     * 管理人员的姓名
     */
    private String realName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间 更新时间
     */
    private LocalDateTime updatedTime;

}
