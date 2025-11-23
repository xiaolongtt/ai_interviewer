package com.xiaolintt.ZhipuAi;

import ai.z.openapi.ZhipuAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/22 20:50
 * @注释
 */
@Configuration
public class ZhipuAiConfig {

    @Value("${zhiPuAi.API_KEY}")
    private String ZHI_API_KEY;

    @Bean
    public ZhipuAiClient createZhipuAiClient() {
        return ZhipuAiClient.builder()
                .apiKey(ZHI_API_KEY)
                .build();
    }

}
