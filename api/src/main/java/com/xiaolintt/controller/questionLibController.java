package com.xiaolintt.controller;

import com.xiaolintt.Bo.QuestionLibBo;
import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.IQuestionLibService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 19:02
 * @注释
 */
@RestController
@RequestMapping("/questionLib")
public class questionLibController {

    @Resource
    private IQuestionLibService questionLibService;

    /**
     * 创建或更新题目
     * @return
     */
    @PostMapping("/createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody QuestionLibBo questionLibBo){
        questionLibService.createOrUpdate(questionLibBo);
        return GraceJSONResult.Success();
    }

    /**
     * 题目列表查询
     * @param aiName
     * @param question
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public GraceJSONResult list(@RequestParam String aiName, @RequestParam String question,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PageGraceResult pageGraceResult = questionLibService.list(aiName, question, page, pageSize);
        return GraceJSONResult.Success(pageGraceResult);
    }


    @PostMapping("/hide")
    public GraceJSONResult hide(@RequestParam String questionLibId){
        System.out.println("hide");
        System.out.println(questionLibId);
        questionLibService.hide(questionLibId);
        return GraceJSONResult.Success();
    }

    @PostMapping("/show")
    public GraceJSONResult show(@RequestParam String questionLibId){
        questionLibService.show(questionLibId);
        return GraceJSONResult.Success();
    }

    @PostMapping("/delete")
    public GraceJSONResult delete(@RequestParam String questionLibId){
        questionLibService.delete(questionLibId);
        return GraceJSONResult.Success();
    }

    @GetMapping("/prepareQuestion")
    public GraceJSONResult prepareQuestion(@RequestParam String candidateId){
        return GraceJSONResult.Success(questionLibService.prepareQuestion(candidateId));
    }
}
