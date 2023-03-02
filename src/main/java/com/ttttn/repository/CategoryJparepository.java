package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Category;

public interface CategoryJparepository extends JpaRepository<Category, Integer>{
  
// @Query(value="select * from category where categoryid =1 ", nativeQuery = true)
// Category finbyidCategory();
//  
// @Query(value="select * from category ", nativeQuery = true)
// Category getCategory();
}
