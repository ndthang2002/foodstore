package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.dto.OrderAccDto;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;

@Repository
public interface OrderJparepository  extends JpaRepository<Order, Integer>{

  @Query(value="SELECT * FROM  orders  WHERE  user_id =?1" , nativeQuery =true)
  List<Order> findOrderByUser (Integer idU);
  
}
