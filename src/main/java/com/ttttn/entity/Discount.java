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
@Table(name = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer discountid;
  private String codevourcher;
  private  int quantity;
  private boolean active;
  @ManyToOne
  @JoinColumn(name="user_id")
  private Account user;
}
  