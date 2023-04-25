package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.DeliveryMethod;

@Repository
public interface DeliveryMethodJparepository extends JpaRepository<DeliveryMethod,Integer>{

  @Query(value="select * from delivery_method where order_id =?1 ", nativeQuery = true)
  DeliveryMethod finDeliveryMethod(Integer orderid);
}
