package com.xiaolintt.service.impl;

import com.xiaolintt.service.IFileService;
import com.xiaolintt.storageMinio.MinIOUtils;
import com.xiaolintt.storageMinio.MinIoConfig;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 13:05
 * @注释
 */
@Service
public class FileServiceImpl implements IFileService {

    @Resource
    private MinIoConfig minIoConfig;


    @Override
    public String uploadInterviewerImage(MultipartFile file) throws Exception {
        String filename ="interviewer_"+ dealFilename(file.getOriginalFilename());
        return MinIOUtils.uploadFile(minIoConfig.getBucketName(), filename, file.getInputStream(), true);
    }

    @Override
    public String uploadInterviewerVideo(MultipartFile file) throws Exception {
        String filename ="interviewerVideo_"+ dealFilename(file.getOriginalFilename());
        return MinIOUtils.uploadFile(minIoConfig.getBucketName(), filename, file.getInputStream(), true);
    }

    private String dealFilename(String filename){
        String fileExtension = filename.substring(filename.lastIndexOf("."));//获取到文件后缀
        filename = UUID.randomUUID()+fileExtension;
        return filename;
    }

}
