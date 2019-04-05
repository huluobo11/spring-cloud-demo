package com.imooc.product.consumer.service.impl;


import com.alibaba.fastjson.JSON;
import com.imooc.product.common.dto.DecreaseStockInput;
import com.imooc.product.common.dto.ProductInfoOutput;
import com.imooc.product.common.enums.ProductStatusEnum;
import com.imooc.product.common.enums.ResultEnum;
import com.imooc.product.common.exception.ProductException;
import com.imooc.product.consumer.convert.ProductInfo2ProductOutPut;
import com.imooc.product.consumer.dataobject.ProductInfo;
import com.imooc.product.consumer.repository.ProductInfoRepository;
import com.imooc.product.consumer.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findProductList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        //数据转换
        List<ProductInfoOutput> ProductInfoOutputList = productInfoList.stream().map(it -> ProductInfo2ProductOutPut.INSTANCE.from(it)).collect(Collectors.toList());
        // 发送MQ消息
        amqpTemplate.convertAndSend("productInfo", JSON.toJSONString(ProductInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        // 如果没有商品，则不操作
        if (CollectionUtils.isEmpty(decreaseStockInputList)) {
        }
        decreaseStockInputList.forEach(it -> {
            Optional<ProductInfo> optional = productInfoRepository.findById(it.getProductId());
            if (!optional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            // 如果商品存在
            ProductInfo productInfo = optional.get();
            int result = productInfo.getProductStock() - it.getProductQuantity();
            // 如果库存不够
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            // 扣掉库存
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        });
        return productInfoList;
    }
}
