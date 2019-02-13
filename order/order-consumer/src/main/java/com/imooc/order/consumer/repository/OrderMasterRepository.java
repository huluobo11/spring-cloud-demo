package com.imooc.order.consumer.repository;

import com.imooc.order.consumer.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
