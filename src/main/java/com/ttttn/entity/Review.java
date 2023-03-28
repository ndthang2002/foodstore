package com.ttttn.entity;

import java.util.Date;

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
  private Account user ;
 
  @ManyToOne
  @JoinColumn(name = "productid")
  Product product;
  
}
