package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.CartProduct;
@Repository
public interface CartProductJparepository  extends JpaRepository<CartProduct, Integer>{

}
