package com.ttttn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Product;

@Repository
public interface ProductJparepository extends JpaRepository<Product, Integer>{

  @Query(value="SELECT * FROM product WHERE  categoryid=?1",nativeQuery = true)
  List<Product> findByCategoryId(String cid);
  
  @Query(value="SELECT * FROM product WHERE  name like  ?1",nativeQuery = true)
  List<Product> findByKeyword(String keyword);
}
