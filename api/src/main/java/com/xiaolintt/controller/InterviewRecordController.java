package com.xiaolintt.controller;

import com.xiaolintt.Bo.SubmitAnswerBO;
import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.IInterviewRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/22 21:08
 * @注释
 */
@RestController
@RequestMapping("/interviewRecord")
public class InterviewRecordController {
    @Resource
    private IInterviewRecordService interviewRecordService;


    @PostMapping("/collect")
    public GraceJSONResult collect(@RequestBody SubmitAnswerBO submitAnswerBO) throws Exception{
        interviewRecordService.display(submitAnswerBO);
        return GraceJSONResult.Success();
    }

    @GetMapping("/list")
    public GraceJSONResult list(@RequestParam String realName, @RequestParam String mobile, @RequestParam(defaultValue = "1", name = "page") Integer page, @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PageGraceResult gridResult = interviewRecordService.list(realName, mobile, page, pageSize);
        return GraceJSONResult.Success(gridResult);
    }
}
