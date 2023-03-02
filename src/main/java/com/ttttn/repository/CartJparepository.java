package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Cart;

@Repository
public interface CartJparepository extends JpaRepository<Cart, Integer>{

}
