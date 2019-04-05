package com.imooc.product.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductInfoOutput {
    private String productId;

    // 名字
    private String productName;
    // 单价
    private BigDecimal  productPrice;
    //  库存
    private Integer productStock;
    // 描述
    private String productDescription;
    // 小图
    private String productIcon;
    // 状态 0-正常，1-下架
    private Integer productStatus;
    // 分类
    private Integer categoryType;
   // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

}
