package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Order;
import com.ttttn.entity.Payment;

@Repository
public interface PayJparepository extends JpaRepository<Payment, Integer>{

  @Query(value="SELECT * FROM  payment  WHERE  order_id =?1" , nativeQuery =true)
  List<Payment> findPayByOrder (Integer orderid);
}
