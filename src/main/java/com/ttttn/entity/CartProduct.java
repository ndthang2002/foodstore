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
@Table(name="cartproduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cartproductid;
  
  @ManyToOne
  @JoinColumn(name="product_id")
  Product product;
  
  @ManyToOne
  @JoinColumn(name="cart_id")
  Cart cart;
    
}
