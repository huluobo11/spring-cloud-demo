package com.imooc.product.common.vo;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static Result success(){
         return success(null);
    }

    public static Result success(Object data){
        Result<Object> result = new Result<>();
        result.setMsg("成功");
        result.setCode(0);
        result.setData(data);
        return result;
    }
}
