package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Order;
import com.ttttn.repository.OrderJparepository;
import com.ttttn.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService{

  @Autowired
  OrderJparepository orderJparepository;
  @Override
  public Order insert(Order order) {
    // TODO Auto-generated method stub
    return orderJparepository.save(order);
  }
  @Override
  public void delete(Order order) {
    // TODO Auto-generated method stub
    orderJparepository.delete(order);
  }
  @Override
  public List<Order> findOrderByUser(Integer idU) {
    // TODO Auto-generated method stub
    return orderJparepository.findOrderByUser(idU);
  }
  @Override
  public List<Order> findAll() {
    // TODO Auto-generated method stub
    return orderJparepository.findAll();
  }

}
