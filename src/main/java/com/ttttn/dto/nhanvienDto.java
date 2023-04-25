package com.ttttn.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ttttn.entity.Account;
import com.ttttn.entity.Address;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Cart;
import com.ttttn.entity.Comment;
import com.ttttn.entity.Discount;
import com.ttttn.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class nhanvienDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userid;
  private String email;
  private String name;
  private String image;
  private String phone;
  private Address address;
  private String username;
  private String password;
}
