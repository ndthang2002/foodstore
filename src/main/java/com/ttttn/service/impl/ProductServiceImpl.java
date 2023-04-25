package com.ttttn.service.impl;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public Page<Product> findByCategoryId(String id) {
    // TODO Auto-generated method stub
    Page<Product> pageProduct = new PageImpl<Product>(productJparepository.findByCategoryId(id) );
    return  pageProduct;
  }

  @Override
  public Product findById(Integer id) {
    // TODO Auto-generated method stub
    return productJparepository.findById(id).get();
  }

  @Override
  public Page<Product> findProductByName(String name) {
    // TODO Auto-generated method stub
    Page<Product> pageProduct = new PageImpl<Product>(productJparepository.findByKeyword(name) );
    return pageProduct;
  }

  @Override
  public Page<Product> findPaginated(Pageable pageable) {
    // TODO Auto-generated method stub
    // TODO Auto-generated method stub
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;
    List<Product> list;
    List<Product> listprodList = productJparepository.findAll();
    if(listprodList.size() < startItem) {
      list = Collections.emptyList();
    }else {
      int toIndex = Math.min(startItem + pageSize,listprodList.size());
      list = listprodList.subList(startItem, toIndex);
    }
    Page<Product> coursePage = new PageImpl<Product>(list,PageRequest.of(currentPage, pageSize),listprodList.size());
    
    return coursePage;
   
  }

  @Override
  public Product insert(Product product) {
    // TODO Auto-generated method stub
    return productJparepository.save(product);
  }

  @Override
  public void delete(Product product) {
    // TODO Auto-generated method stub
    productJparepository.delete(product);
    
  }

  @Override
  public Integer findIdCaByIdP(Integer idP) {
    // TODO Auto-generated method stub
    return productJparepository.findIdCaByIdP(idP);
  }






  
  
  
}
