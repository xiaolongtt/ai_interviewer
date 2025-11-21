package com.xiaolintt.enums;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/21 22:24
 * @注释
 */
public enum RedisKeyEnum {
    SMS_CODE("sms:code:"),
    TOKEN("token:");
    private String KEY;
    RedisKeyEnum(String KEY) {
        this.KEY = KEY;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }
}
