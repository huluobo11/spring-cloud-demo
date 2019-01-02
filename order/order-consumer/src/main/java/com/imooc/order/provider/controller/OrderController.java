package com.imooc.order.provider.controller;


import com.imooc.order.common.enums.ResultEnum;
import com.imooc.order.common.exception.OrderException;
import com.imooc.order.common.vo.Result;
import com.imooc.order.provider.dto.OrderDTO;
import com.imooc.order.provider.converter.OrderForm2OrderDTO;
import com.imooc.order.provider.form.OrderForm;
import com.imooc.order.provider.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 生成订单信息
     * @param orderForm
     */
    @PostMapping("/create")
    public Result createOrder(@Valid @RequestBody OrderForm orderForm,
                                    BindingResult bindingResult) throws Exception {
        // 1、参数校验
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.INSTANCE.converter(orderForm);

        String orderId = orderService.createOrder(orderDTO);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId", orderId);
        return Result.success(hashMap);
    }

}
