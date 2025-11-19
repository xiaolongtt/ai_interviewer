package com.xiaolintt.controller;

import com.xiaolintt.Bo.CandidateBO;
import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.ICandidateService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 10:46
 * @注释
 */
@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Resource
    private ICandidateService candidateService;

    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody CandidateBO candidateBO) {
        candidateService.createOrUpdate(candidateBO);
        return GraceJSONResult.Success();
    }

    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String realName, @RequestParam String mobile,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PageGraceResult gridResult = candidateService.queryList(realName, mobile, page, pageSize);
        return GraceJSONResult.Success(gridResult);
    }

    @GetMapping("detail")
    public GraceJSONResult detail(@RequestParam String candidateId) {
        return GraceJSONResult.Success(candidateService.getDetail(candidateId));
    }

    @PostMapping("delete")
    public GraceJSONResult delete(@RequestParam String candidateId) {
        candidateService.delete(candidateId);
        return GraceJSONResult.Success();
    }
}
