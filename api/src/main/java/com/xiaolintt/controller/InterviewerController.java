package com.xiaolintt.controller;

import com.xiaolintt.Bo.InterviewerBo;
import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.service.IInterviewerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 21:32
 * @注释
 */
@RestController
@RequestMapping("/interviewer")
public class InterviewerController {

    @Resource
    private IInterviewerService interviewerService;

    /**
     * 创建或更新面试官(根据是否传送id判断是创建还是更新)
     * @param interviewerBo
     * @return
     * 加上了 @Valid 注解，该注解会对 interviewerBo 进行校验，校验规则在 InterviewerBo 中定义.列如NotBlank等
     */
    @PostMapping("/createOrUpdate")
    public GraceJSONResult createOrUpdate(@Valid @RequestBody InterviewerBo interviewerBo){
        interviewerService.createOrUpdate(interviewerBo);
        return GraceJSONResult.Success();
    }
    @DeleteMapping("/delete")
    public GraceJSONResult deleteInterviewer(@RequestParam String interviewerId){
        interviewerService.deleteInterviewer(interviewerId);
        return GraceJSONResult.Success();
    }

    @GetMapping("/list")
    public GraceJSONResult listAll(){
        return GraceJSONResult.Success(interviewerService.list());
    }
}
