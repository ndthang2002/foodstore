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
@Table(name = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer discountid;
  private Date startday;
  private Date enddate;
  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;
}
