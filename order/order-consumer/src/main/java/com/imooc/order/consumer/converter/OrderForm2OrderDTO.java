package com.imooc.order.consumer.converter;


import com.imooc.order.consumer.dto.OrderDTO;
import com.imooc.order.consumer.form.OrderForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
  // (componentModel="spring")
public interface OrderForm2OrderDTO {

    OrderForm2OrderDTO INSTANCE = Mappers.getMapper(OrderForm2OrderDTO.class);

    OrderDTO converter(OrderForm orderForm);
}
