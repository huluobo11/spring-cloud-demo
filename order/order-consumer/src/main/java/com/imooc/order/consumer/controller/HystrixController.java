package com.imooc.order.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @GetMapping("/getPruduct")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getPruduct(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8082/product/product/listForOrder", Arrays.asList("157875196366160022"), String.class);
        // throw new RuntimeException("抛出异常");
    }

    private String fallback() {
        return "fallback";
    }

    private String defaultFallback() {
        return "defaultFallback";
    }
}
