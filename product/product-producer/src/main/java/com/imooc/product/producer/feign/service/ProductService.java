package com.imooc.product.producer.feign.service;

import com.imooc.product.producer.dto.DecreaseStockInput;
import com.imooc.product.producer.dto.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "product")
@RequestMapping("/product")
public interface ProductService {

    @GetMapping("msg2")
    String productMsg2();

    @PostMapping("listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
