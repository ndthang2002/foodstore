package com.ttttn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderid;
  private Date bookingdate;
  private Date deliverydate;  
  private String orderstatus;
  private double totalmoney;

  
  @ManyToOne
  @JoinColumn(name="user_id")
  private Account user;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<Cart> listCarts;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<Pay> pays;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<DeliveryMethod> deliveryMothods;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<OrderItems> orderItems;
  
  
}
