package com.xiaolintt.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 13:05
 * @注释
 */

public interface IFileService {
    String uploadInterviewerImage(MultipartFile file) throws Exception;
}
