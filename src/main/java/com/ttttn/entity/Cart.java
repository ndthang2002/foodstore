package com.ttttn.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

  @JsonIgnore
   @OneToMany(mappedBy = "cart")
   private List<Order> orders;
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
  private User user;
}
