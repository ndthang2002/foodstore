package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Order;

public interface OrderService {
  Order insert(Order order);
  void delete(Order order);
  List<Order> findOrderByUser(Integer idU);
  List<Order> findAll();
  
}
