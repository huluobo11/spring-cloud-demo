package com.imooc.order.common.exception;

import com.imooc.order.common.enums.ResultEnum;
import lombok.Data;

@Data
public class OrderException extends RuntimeException{

    private Integer code;

    private String message;

    public OrderException(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
    public OrderException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
