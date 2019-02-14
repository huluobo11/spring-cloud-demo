package com.imooc.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private String index;

    @Autowired
    private Environment environment;

    @GetMapping("/test")
    public String test(){
        index = environment.getProperty("${spring.application.index}","没有值。。。");
        return index;
    }
}
