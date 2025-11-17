package com.xiaolintt.controller;

import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.service.IFileService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 12:53
 * @注释
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private IFileService fileService;

    /**
     * 上传面试者图片
     * @return
     */
    @PostMapping("/uploadInterviewerImage")
    public GraceJSONResult uploadInterviewerImage(@RequestParam("file") MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if(StringUtils.isBlank(filename)){
            return GraceJSONResult.ErrorMsg("文件名不能为空");
        }
        return GraceJSONResult.Success(fileService.uploadInterviewerImage(file));
    }


    /**
     * 上传面试者视频
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadInterviewVideo")
    public GraceJSONResult uploadInterviewerVideo(@RequestParam("file") MultipartFile file) throws Exception{
        String filename = file.getOriginalFilename();
        if(StringUtils.isBlank(filename)){
            return GraceJSONResult.ErrorMsg("文件名不能为空");
        }
        return GraceJSONResult.Success(fileService.uploadInterviewerVideo(file));
    }

}
