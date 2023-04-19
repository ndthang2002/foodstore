package com.ttttn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Order;
import com.ttttn.repository.OrderJparepository;
import com.ttttn.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService{

  @Autowired
  OrderJparepository oreJparepository;
  @Override
  public Order insert(Order order) {
    // TODO Auto-generated method stub
    return oreJparepository.save(order);
  }

}
