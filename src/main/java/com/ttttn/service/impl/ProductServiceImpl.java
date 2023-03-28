package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Product;
import com.ttttn.repository.ProductJparepository;
import com.ttttn.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
  @Autowired
  ProductJparepository productJparepository;

  @Override
  public List<Product> fillAll() {
    // TODO Auto-generated method stub
    return productJparepository.findAll();
  }

  @Override
  public List<Product> findByCategoryId(String id) {
    // TODO Auto-generated method stub
    return productJparepository.findByCategoryId(id);
  }

  @Override
  public Product findById(Integer id) {
    // TODO Auto-generated method stub
    return productJparepository.findById(id).get();
  }

  @Override
  public List<Product> findProductByName(String name) {
    // TODO Auto-generated method stub
    return productJparepository.findByKeyword(name);
  }

  
  
  
}
