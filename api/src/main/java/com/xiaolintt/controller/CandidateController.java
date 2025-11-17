package com.xiaolintt.controller;

import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.service.ICandidateService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 应聘者列表查询
     * @param realName
     * @param mobile
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public GraceJSONResult list(@RequestParam String realName, @RequestParam String mobile,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {

        return null;
    }
}
