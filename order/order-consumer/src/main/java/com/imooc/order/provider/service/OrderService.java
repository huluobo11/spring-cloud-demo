package com.imooc.order.provider.service;

import com.imooc.order.provider.dto.OrderDTO;

public interface OrderService {
    String createOrder(OrderDTO orderDTO);
}
