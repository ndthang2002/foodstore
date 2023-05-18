package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.OrderItems;

public interface OrderItemService {

   OrderItems insert(OrderItems orderItems);
   OrderItems findOrderItemsbyOrder(Integer orderid);
   void delete(OrderItems orderItems);
   List<OrderItems> findAll();
}
