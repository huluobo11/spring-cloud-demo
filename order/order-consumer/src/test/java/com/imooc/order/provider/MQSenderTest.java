package com.imooc.order.provider;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQSenderTest extends OrderConsumerApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    //@Test
    public void send(){
        amqpTemplate.convertAndSend("myOrderQueue", "我的第一个order-mq 测试");
    }
}
