package com.ttttn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;
import com.ttttn.repository.OrderItemJparepository;
import com.ttttn.service.OrderItemService;
import com.ttttn.service.OrderService;

@Service
public class OrderItemServiceImpl implements OrderItemService
{
  @Autowired
  OrderItemJparepository orderItemJparepository;

  @Override
  public OrderItems insert(OrderItems orderItems) {
    // TODO Auto-generated method stub
    return orderItemJparepository.save(orderItems);
  }

}
