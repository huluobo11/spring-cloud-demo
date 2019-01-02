package com.imooc.product.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ServerController {

    @GetMapping("msg")
    public String msg() {

        return "this is product msg";
    }
}
