package com.imooc.order.provider.converter;


import com.imooc.order.provider.dto.OrderDTO;
import com.imooc.order.provider.form.OrderForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderForm2OrderDTO {

    OrderForm2OrderDTO INSTANCE = Mappers.getMapper(OrderForm2OrderDTO.class);

    @Mappings({
            @Mapping(source="sname", target="username")
    })
    OrderDTO converter(OrderForm orderForm);
}
