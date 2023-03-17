package com.ttttn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer orderitemid;

@ManyToOne(fetch =FetchType.LAZY)
@JoinColumn(name = "order_id")
private Order order;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="product_id")
private Product product;

@Column(name="quantity")
private int quantity;

}
