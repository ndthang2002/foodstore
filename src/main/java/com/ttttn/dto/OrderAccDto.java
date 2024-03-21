package com.ttttn.dto;

import java.util.Date;

import com.ttttn.entity.Account;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;
import com.ttttn.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAccDto {

//  private OrderItems orderItem;
  private int quantityProduct;
//  private Order order;
  private Product product;
  private Date bookingDate;
  private Date deliveryDate;
  private String orderStatus;
  private double totalMoney;
  private Account user;
  
}
