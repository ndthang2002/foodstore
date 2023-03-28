package com.ttttn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
