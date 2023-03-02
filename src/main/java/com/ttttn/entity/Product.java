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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@OneToMany(mappedBy = "product")
private List<Comment> comments;

@OneToMany(mappedBy = "product")
private List<Discount> discounts;

@OneToMany(mappedBy = "product")
private List<Review> reviews;

//@ManyToMany
//@JoinTable(name = "order_items",
//joinColumns = @JoinColumn(name="product_id"),
//inverseJoinColumns = @JoinColumn(name="order_id"))
//private List<Order> orders = new ArrayList<>();

@OneToMany(mappedBy = "product")
private List<OrderItems> orderItems;

}
