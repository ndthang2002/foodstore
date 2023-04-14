package com.ttttn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartDto {
  
  private Integer cartid;
  private Integer productid;
  private String imageproduct;
  private String nameproduct;
  private double price;
  private int quantity;
  private double total;
  private String desciption;
}