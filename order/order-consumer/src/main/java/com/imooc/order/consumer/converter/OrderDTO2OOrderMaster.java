package com.imooc.order.consumer.converter;


import com.imooc.order.consumer.dto.OrderDTO;
import com.imooc.order.consumer.dataobject.OrderMaster;
import com.imooc.order.consumer.form.OrderForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDTO2OOrderMaster {

    OrderDTO2OOrderMaster INSTANCE = Mappers.getMapper(OrderDTO2OOrderMaster.class);

    OrderMaster from(OrderDTO orderDTO);

    OrderDTO from(OrderForm orderForm);
}
