package com.ttttn.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userid;
  private String address;
  private String email;
  private String name;
  private String phone;
  private boolean role;
  @OneToMany(mappedBy = "user")
  private List<Comment> comments;
  @OneToMany(mappedBy = "user")
  private List<Cart> carts;
  @OneToMany(mappedBy = "user")
   private List<Order> orders;
  @OneToMany(mappedBy = "user")
  private List<Authorities> authorities;
  
  
  @ManyToMany
  @JoinTable(name = "authorities",
  joinColumns = @JoinColumn(name="userid"),
  inverseJoinColumns = @JoinColumn(name="roleid"))
  private List<Role> roles = new ArrayList<>();
}
