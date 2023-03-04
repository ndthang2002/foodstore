package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Category;
import com.ttttn.repository.CategoryJparepository;
import com.ttttn.service.CategoryService;

@Service
public class CategoryServiceImple implements CategoryService{
@Autowired
CategoryJparepository categoryJparepository;
  @Override
  public List<Category> findAll() {
    // TODO Auto-generated method stub
    return categoryJparepository.findAll();
  }

}
