package com.ttttn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  private int totalmoney;

  
  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
  
  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<Pay> pays;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<DeliveryMothod> deliveryMothods;
  
  @JsonIgnore
  @OneToMany(mappedBy = "order")
  private List<OrderItems> orderItems;
  
  
}
