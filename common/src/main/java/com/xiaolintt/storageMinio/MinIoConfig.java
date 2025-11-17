package com.xiaolintt.storageMinio;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 11:17
 * @注释 minio工厂类，创建一个minio工具类
 */
@Data
@Configuration
public class MinIoConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.fileHost}")
    private String fileHost;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinIOUtils createMinIOUtils() {
        return new MinIOUtils(endpoint, fileHost, bucketName, accessKey, secretKey);
    }
}
