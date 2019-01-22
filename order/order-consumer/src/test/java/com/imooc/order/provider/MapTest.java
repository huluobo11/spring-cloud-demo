package com.imooc.order.provider;

import com.imooc.order.provider.converter.OrderForm2OrderDTO;
import com.imooc.order.provider.dto.OrderDTO;
import com.imooc.order.provider.form.OrderForm;
import org.junit.Test;

public class MapTest {

    //@Test
    public void tests(){
        OrderForm orderForm = new OrderForm();
        orderForm.setBuyerName("ming");
        OrderDTO converter = OrderForm2OrderDTO.INSTANCE.converter(orderForm);
        System.out.println(converter);
    }
}
