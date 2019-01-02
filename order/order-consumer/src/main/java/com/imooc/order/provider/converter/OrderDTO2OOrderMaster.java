package com.imooc.order.provider.converter;


import com.imooc.order.provider.dto.OrderDTO;
import com.imooc.order.provider.dataobject.OrderMaster;
import com.imooc.order.provider.form.OrderForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDTO2OOrderMaster {

    OrderDTO2OOrderMaster INSTANCE = Mappers.getMapper(OrderDTO2OOrderMaster.class);

    OrderMaster from(OrderDTO orderDTO);

    OrderDTO from(OrderForm orderForm);
}
