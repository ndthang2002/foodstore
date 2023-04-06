package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Cart;

@Repository
public interface CartJparepository extends JpaRepository<Cart, Integer>{
  @Query(value="SELECT  cartid from cart where user_id=?1" , nativeQuery =true)
  Integer findIdCartByUserid (Integer id);
}
