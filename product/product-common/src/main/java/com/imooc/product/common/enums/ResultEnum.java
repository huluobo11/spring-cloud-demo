package com.imooc.product.common.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXISTS(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存错误"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
