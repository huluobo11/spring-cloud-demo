package com.imooc.order.consumer.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收方
 */
@Slf4j
@Component
public class FirstMQReceiver {
    //@RabbitListener(queues = "myOrderQueue") 指定接收的队列
    //@RabbitListener(queuesToDeclare = @Queue("myOrderQueue")) //自动创建队列
    @RabbitListener(bindings ={
            @QueueBinding(value = @Queue("myOrderQueue"), exchange = @Exchange("myOrderExchange"))
    })                                    //创建队列并绑定交换机，
    public void receive(String message){
        log.info("order-mq-receive:{}", message);
    }
}
