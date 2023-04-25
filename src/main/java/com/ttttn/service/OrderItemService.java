package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.OrderItems;

public interface OrderItemService {

   OrderItems insert(OrderItems orderItems);
   List<OrderItems> findOrderItemsbyOrder(Integer orderid);
   void delete(OrderItems orderItems);
}
