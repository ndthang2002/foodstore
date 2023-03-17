package com.ttttn.entity;

import java.sql.Date;

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
@Table(name="image_product")
@NoArgsConstructor
@AllArgsConstructor
public class ImageProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer imageproductid;
  private String urlname;  
  private String imagedescription;
  private Date  datecreated;
  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
  
  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;
  
}
