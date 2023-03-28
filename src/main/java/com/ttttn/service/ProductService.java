package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Product;

public interface ProductService {
 List<Product> fillAll();
 
List<Product> findByCategoryId(String id);
Product findById(Integer id);
List<Product> findProductByName(String name);

}
