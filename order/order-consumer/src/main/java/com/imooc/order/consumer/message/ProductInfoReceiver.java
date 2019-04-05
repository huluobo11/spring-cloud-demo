package com.imooc.order.consumer.message;

import com.alibaba.fastjson.JSON;
import com.imooc.product.common.dto.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare ={@Queue("productInfo")} )
    public void process(String messgage){
        // message => productInfoOutputList
        List<ProductInfoOutput> productInfoOutputList = JSON.parseArray(messgage, ProductInfoOutput.class);
        log.info("receive from ={},message ={}", "productInfo", productInfoOutputList);
        // 保存到redis中去
        productInfoOutputList.forEach(it -> {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, it.getProductId()), String.valueOf(it.getProductStock()));
        });
    }
}
