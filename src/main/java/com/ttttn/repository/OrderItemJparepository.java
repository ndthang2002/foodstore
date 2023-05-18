package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.OrderItems;

@Repository
public interface OrderItemJparepository extends JpaRepository<OrderItems, Integer>{
  
  @Query(value="SELECT  * FROM  order_items  WHERE order_id = ?1" , nativeQuery =true)
  OrderItems findOrderItemByOrder (Integer orderid);
}
