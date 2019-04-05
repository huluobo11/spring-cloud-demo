package com.imooc.product.producer.feign.service.fallback;

import com.imooc.product.common.dto.DecreaseStockInput;
import com.imooc.product.common.dto.ProductInfoOutput;
import com.imooc.product.producer.feign.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceFallBack implements ProductService {

    @Override
    public String productMsg2() {
        return "fallback";
    }

    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}
