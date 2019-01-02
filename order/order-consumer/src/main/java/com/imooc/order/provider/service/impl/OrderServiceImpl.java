package com.imooc.order.provider.service.impl;


import com.imooc.order.common.enums.OrderStatusEnum;
import com.imooc.order.common.enums.PayStatusEnum;
import com.imooc.order.common.util.KeyUtil;
import com.imooc.order.provider.dto.OrderDTO;
import com.imooc.order.provider.converter.OrderDTO2OOrderMaster;
import com.imooc.order.provider.dataobject.OrderDetail;
import com.imooc.order.provider.dataobject.OrderMaster;
import com.imooc.order.provider.repository.OrderDetailRepository;
import com.imooc.order.provider.repository.OrderMasterRepository;
import com.imooc.order.provider.service.OrderService;
import com.imooc.product.producer.dto.DecreaseStockInput;
import com.imooc.product.producer.dto.ProductInfoOutput;
import com.imooc.product.producer.feign.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    public String createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // 2、调用商品服务，查询商品信息
        List<String> idList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productService.listForOrder(idList);
        Map<String, ProductInfoOutput> idProductInfoMap = productInfoList.stream().collect(Collectors.toMap(ProductInfoOutput::getProductId, it -> it));
        // 3、计算总价
        Map<String, BigDecimal> idPriceMap = productInfoList.stream().collect(Collectors.toMap(ProductInfoOutput::getProductId, ProductInfoOutput::getProductPrice));
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail it : orderDTO.getOrderDetailList()) {
            total = new BigDecimal(it.getProductQuantity()).multiply(idPriceMap.get(it.getProductId())).add(total);
            BeanUtils.copyProperties(idProductInfoMap.get(it.getProductId()), it);
            it.setOrderId(orderId);
            it.setDetailId(KeyUtil.genUniqueKey());
            it.setCreateTime(null);
            it.setUpdateTime(null);
        }
        // 4、商品服务，扣库存（一般情况应该是在支付后扣库存，在下单时只创建订单即可）
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        // 5、订单服务，订单入库(数据落地)
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(total);
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster orderMaster = OrderDTO2OOrderMaster.INSTANCE.from(orderDTO);
        // 保存订单信息和订单详情信息
        OrderMaster obj = orderMasterRepository.save(orderMaster);
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<OrderDetail> list = orderDetailRepository.saveAll(orderDetailList);
       if (null != obj && !CollectionUtils.isEmpty(list)) {
           return orderDTO.getOrderId();
       }
       return null;
    }
}
