package com.imooc.order.consumer.service;

import com.imooc.order.consumer.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单，买家操作
     * @param orderDTO
     */
    String createOrder(OrderDTO orderDTO);

    /**
     * 完成订单，卖家操作
     * @param orderId
     */
    OrderDTO finishOrder(String orderId);
}
