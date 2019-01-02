package com.imooc.product.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductProviderApplication.class, args);
    }
}
