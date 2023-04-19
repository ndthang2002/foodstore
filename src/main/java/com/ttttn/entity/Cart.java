package com.ttttn.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cartid;
  private int quantityproduct;
  private double totalall;  
  private Date datecreated;

   @ManyToOne
   @JoinColumn(name="order_id")
   private Order order;
   /*
    * @ManyToMany(cascade = {CascadeType.ALL})
    * 
    * @JoinTable( name="cart_product", joinColumns = {@JoinColumn(name
    * ="cart_id")}, inverseJoinColumns = {@JoinColumn(name="product_id")}) private
    * List<Product>products;
    */
  @JsonIgnore
 @OneToMany(mappedBy = "cart")
 private List<CartProduct> cartProducts;
 
  @ManyToOne
  @JoinColumn(name = "user_id")
  private Account user;
}
