package com.imooc.order.common.exception;

import lombok.Data;

@Data
public class OrderException extends Exception{

    private Integer code;

    private String message;

    public OrderException(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
