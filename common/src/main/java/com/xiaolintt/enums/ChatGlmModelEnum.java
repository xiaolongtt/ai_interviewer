package com.xiaolintt.enums;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/23 19:31
 * @注释
 */
public enum ChatGlmModelEnum {

    GLM_4_FLASHX("glm-4-flashx"),
    GLM_4_6("glm-4.6"),
    GLM_4_FLASH("glm-4-flash");

    private final String model;

    ChatGlmModelEnum(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
