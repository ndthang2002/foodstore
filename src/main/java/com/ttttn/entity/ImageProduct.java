package com.ttttn.entity;

import java.sql.Date;

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
  private Account user;
  
  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;
  
}
