package com.xiaolintt.controller;

import com.xiaolintt.Bo.InterviewerBo;
import com.xiaolintt.Bo.JobBo;
import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.service.IJobService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 23:39
 * @注释
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private IJobService jobService;

    @PostMapping("/createOrUpdate")
    public GraceJSONResult createOrUpdate(@Valid @RequestBody JobBo jobBo){
        jobService.createOrUpdate(jobBo);
        return GraceJSONResult.Success();
    }

    @GetMapping("/list")
    public GraceJSONResult listAll(@RequestParam Integer page, @RequestParam Integer pageSize){
        return GraceJSONResult.Success(jobService.list(page, pageSize));
    }
    @PostMapping ("/delete")
    public GraceJSONResult delete(@RequestParam String jobId) {
        jobService.delete(jobId);
        return GraceJSONResult.Success();
    }

    @GetMapping("/detail")
    public GraceJSONResult list(@RequestParam String jobId) {
        return GraceJSONResult.Success(jobService.getDetail(jobId));
    }

    @GetMapping("/nameList")
    public GraceJSONResult nameList() {
        return GraceJSONResult.Success(jobService.getNameList());
    }

}
