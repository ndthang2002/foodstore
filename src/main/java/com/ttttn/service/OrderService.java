package com.ttttn.service;

import java.util.List;

import com.ttttn.dto.OrderAccDto;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;

public interface OrderService {
  Order insert(Order order);
  void delete(Order order);
  List<Order> findOrderByUser(Integer idU);
  List<Order> findAll();
 Order findById(Integer id);
}
