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
    @NotBlank
    @JsonProperty("name")
    private String buyerName;
    @NotBlank
    @JsonProperty("phone")
    private String buyerPhone;
    @NotBlank
    @JsonProperty("address")
    private String buyerAddress;
    @NotBlank
    @JsonProperty("openid")
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    @NotEmpty
    @JsonProperty("items")
    private List<OrderDetail> orderDetailList;
}
