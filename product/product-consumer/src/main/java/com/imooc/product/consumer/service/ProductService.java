package com.imooc.product.consumer.service;

import com.imooc.product.consumer.dataobject.ProductInfo;
import com.imooc.product.consumer.dto.CartDTO;

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
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
