package com.ttttn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.entity.Category;
import com.ttttn.entity.Comment;
import com.ttttn.entity.Product;
import com.ttttn.repository.CategoryJparepository;
import com.ttttn.repository.ProductJparepository;


@RestController
public class index {
@Autowired 
 ProductJparepository productJparepository;
@Autowired
CategoryJparepository categoryJparepository;
@PostMapping("/index")
public Category add() {
  Category ca = new Category();
  ca.setName("đồ tươi sống");
  categoryJparepository.save(ca);
  return ca;
}
@GetMapping("/index")
public List<Category> get() {
  List<Category> list;
 list = categoryJparepository.findAll();
  return list;
}

//@GetMapping("/get1")
//public Category  get1() {
//   Category category = new Category();
//   category = categoryJparepository.getCategory();
//  return category;
//}

//@PostMapping("/addproduct")
//public Product addProduct() {
//  Product p = new Product();
//  Category cateid = new Category();
//  cateid = categoryJparepository.finbyidCategory();
//  List<Comment> comments =null;
//  p.setCategory(cateid);
//  p.setComments(comments);
//  p.setImage("sdf12.jpg");
//  p.setModel("nhu mao");
//  p.setName("san pham c");
//  p.setPrice(100);
//  
//  productJparepository.save(p);
//  return p;
//}
  
}
