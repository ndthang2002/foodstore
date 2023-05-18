package com.ttttn.service.impl;

import java.util.List;

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

  @Override
  public OrderItems findOrderItemsbyOrder(Integer orderid) {
    // TODO Auto-generated method stub
    return  orderItemJparepository.findOrderItemByOrder(orderid);
  }

  @Override
  public void delete(OrderItems orderItems) {
    // TODO Auto-generated method stub
    orderItemJparepository.delete(orderItems);
  }

  @Override
  public List<OrderItems> findAll() {
    // TODO Auto-generated method stub
    return orderItemJparepository.findAll();
  }

}
