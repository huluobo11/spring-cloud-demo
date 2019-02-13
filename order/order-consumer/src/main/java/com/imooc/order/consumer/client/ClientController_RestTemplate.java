package com.imooc.order.consumer.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController_RestTemplate {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getProductMsg22")
    public String getProductMsg22() {
        // 1.第一种方式,直接使用restTemplate, URL写死。
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("http://localhost:8081/msg", String.class);
//        log.info("result={}", result);

        // 2.第二种方式，使用LoadBalancerClient通过应用名获取 url ，再用restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        String result = restTemplate.getForObject(url, String.class);
        log.info("result={}", result);

        // 3.第三种方式(使用@LoadBalanced,可在url中直接使用应用名称)，restTemplate必须要在启动类中注册，
//        String result = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("result={}", result);
        return result;
    }

}
