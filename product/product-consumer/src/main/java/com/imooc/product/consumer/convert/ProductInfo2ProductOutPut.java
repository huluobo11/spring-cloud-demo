package com.imooc.product.consumer.convert;

import com.imooc.product.common.dto.ProductInfoOutput;
import com.imooc.product.consumer.dataobject.ProductInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductInfo2ProductOutPut {

    ProductInfo2ProductOutPut INSTANCE = Mappers.getMapper(ProductInfo2ProductOutPut.class);

    ProductInfoOutput from(ProductInfo productInfo);
}
