package com.ttttn.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
  private Integer orderid;
  private Integer quantityorder;
  private String nameaccount;
  private String dateorder;
  private String Status;
  private Double totalmoney;
  
}
