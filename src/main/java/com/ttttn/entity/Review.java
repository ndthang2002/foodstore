package com.ttttn.entity;

import java.util.Date;

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
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor

public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reviewid;
  
  private String content;
  private int star;
  private Date date;
 
  
  @ManyToOne
  @JoinColumn(name = "userid")
  private User user ;
 
  @ManyToOne
  @JoinColumn(name = "productid")
  Product product;
  
}
