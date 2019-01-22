package com.imooc.order.provider.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.order.provider.dataobject.OrderDetail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderForm {

    private String orderId;
    @NotBlank(message = "name不能为空")
    @JsonProperty("name")
    private String buyerName;
    @NotBlank(message = "phone不能为空")
    @JsonProperty("phone")
    private String buyerPhone;
    @NotBlank(message = "address不能为空")
    @JsonProperty("address")
    private String buyerAddress;
    @NotBlank(message = "openid不能为空")
    @JsonProperty("openid")
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    @NotEmpty(message = "items不能为空")
    @JsonProperty("items")
    private List<OrderDetail> orderDetailList;
}
