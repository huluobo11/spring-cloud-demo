package com.imooc.product.consumer.service;

import com.imooc.product.common.dto.DecreaseStockInput;
import com.imooc.product.consumer.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据多个ID，查询商品列表
     * @param productIdList
     */
    List<ProductInfo> findProductList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
