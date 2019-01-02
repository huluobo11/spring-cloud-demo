package com.imooc.product.provider.service.impl;


import com.imooc.product.common.enums.ProductStatusEnum;
import com.imooc.product.common.enums.ResultEnum;
import com.imooc.product.common.exception.ProductException;
import com.imooc.product.provider.dataobject.ProductInfo;
import com.imooc.product.provider.dto.CartDTO;
import com.imooc.product.provider.repository.ProductInfoRepository;
import com.imooc.product.provider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findProductList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        if (!CollectionUtils.isEmpty(cartDTOList)) {
            cartDTOList.forEach(it -> {
                Optional<ProductInfo> optional = productInfoRepository.findById(it.getProductId());
                if (!optional.isPresent()) {
                    throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
                }
                // 如果商品存在
                ProductInfo productInfo = optional.get();
                int result = productInfo.getProductStock() - it.getProductQuantity();
                if (result < 0) {
                    throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
                }
                productInfo.setProductStock(result);
                productInfoRepository.save(productInfo);
            });
        }
    }
}
