package com.imooc.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {"com.imooc.product.producer.feign.service"})
/*@SpringBootApplication
@EnableDiscoveryClient
@@EnableCircuitBreaker*/
@SpringCloudApplication
@ComponentScan(basePackages = "com.imooc")
public class OrderConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerApplication.class, args);
    }
}
