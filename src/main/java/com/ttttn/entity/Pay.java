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
@Table(name = "pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer payid;
  private String paymentmethod;
  private int amountofmoney;
  private String paymentstatus;
  @ManyToOne
  @JoinColumn(name="order_id")
  private Order order;
}
