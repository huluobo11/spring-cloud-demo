package com.imooc.order.consumer.service;

import com.imooc.order.consumer.dto.OrderDTO;

public interface OrderService {
    String createOrder(OrderDTO orderDTO);
}
