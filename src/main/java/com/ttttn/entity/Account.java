package com.ttttn.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userid;
  private String email;
  private String name;
  private String phone;
  @NotEmpty(message = "Username is required")
  private String username;
  private String password;
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Comment> comments;
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Cart> carts;
  @JsonIgnore
  @OneToMany(mappedBy = "user")
   private List<Order> orders;
  @JsonIgnore
  @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
  private List<Authorities> authorities;


  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Discount> discounts;
  
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Address> address;
  
//  @ManyToMany
//  @JoinTable(name = "authorities",
//  joinColumns = @JoinColumn(name="userid"),
//  inverseJoinColumns = @JoinColumn(name="roleid"))
//  private List<Role> roles = new ArrayList<>();
}
