package com.imooc.usercommon.vo;

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
    public static Result error(){
        return error(null);
    }

    public static Result error(Object data) {
        return error("失败", data);
    }
    public static Result error(String msg, Object data) {
        Result<Object> result = new Result<>();
        result.setMsg(msg);
        result.setCode(1);
        result.setData(data);
        return result;
    }
}
