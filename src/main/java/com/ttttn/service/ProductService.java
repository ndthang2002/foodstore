package com.ttttn.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ttttn.entity.Product;

public interface ProductService {
 List<Product> fillAll();
 
Page<Product> findByCategoryId(String id);

Product findById(Integer id);

//tim kiem theo ten
Page<Product> findProductByName(String name);


Page<Product> findPaginated(Pageable pageable);
  

}
