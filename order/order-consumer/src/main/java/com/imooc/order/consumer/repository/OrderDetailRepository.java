package com.imooc.order.consumer.repository;

import com.imooc.order.consumer.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}
