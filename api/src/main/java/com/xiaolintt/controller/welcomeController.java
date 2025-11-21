package com.xiaolintt.controller;

import com.xiaolintt.Bo.VerifySMSBO;
import com.xiaolintt.result.GraceJSONResult;
import com.xiaolintt.service.IWelcomeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/21 21:45
 * @注释
 */

@RestController
@RequestMapping("/welcome")
public class welcomeController {


    @Resource
    private IWelcomeService welcomeService;

    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    @PostMapping("/getSMSCode")
    public GraceJSONResult getCode(@RequestParam String mobile){
        String code = welcomeService.getSMSCode(mobile);
        return GraceJSONResult.Success(code);
    }

    /**
     * 验证用户登录
     * @param verifySMSBO
     * @return
     */
    @PostMapping("/verify")
    public GraceJSONResult verify(@Valid @RequestBody VerifySMSBO verifySMSBO){
        return GraceJSONResult.Success(welcomeService.verify(verifySMSBO));
    }

}
