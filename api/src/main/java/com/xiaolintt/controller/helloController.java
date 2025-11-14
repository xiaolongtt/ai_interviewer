package com.xiaolintt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/14 23:19
 * @注释
 */
@RestController
@RequestMapping("/hello")
public class helloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

}
