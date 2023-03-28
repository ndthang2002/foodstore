package com.ttttn.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer productid;
private int price;
private String image;
private String model;
private int quantity;
private String name;
@ManyToOne
@JoinColumn(name="categoryid")
Category  category;

@JsonIgnore
@OneToMany(mappedBy = "product")
private List<Comment> comments;

//@JsonIgnore
//@OneToMany(mappedBy = "product")
//private List<Discount> discounts;

@JsonIgnore
@OneToMany(mappedBy = "product")
private List<Review> reviews;

@JsonIgnore
@OneToMany(mappedBy = "product")
private List<ImageProduct> imageProducts;

@JsonIgnore
@OneToMany(mappedBy = "product")
private List<CartProduct> cartProducts;
//@ManyToMany
//@JoinTable(name = "order_items",
//joinColumns = @JoinColumn(name="product_id"),
//inverseJoinColumns = @JoinColumn(name="order_id"))
//private List<Order> orders = new ArrayList<>();

@JsonIgnore
@OneToMany(mappedBy = "product")
private List<OrderItems> orderItems;

}
