package com.ttttn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="delivery_method")
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryMothod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer transportid ;
  private int transportfee;
  private String name;
  private double price;
  @ ManyToOne
  @JoinColumn(name="order_id")
  private Order order;
  
}
