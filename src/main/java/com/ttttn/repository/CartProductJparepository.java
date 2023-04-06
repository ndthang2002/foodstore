package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Account;
import com.ttttn.entity.CartProduct;
@Repository
public interface CartProductJparepository  extends JpaRepository<CartProduct, Integer>{

//  @Query(value="SELECT product_id from cartproduct" , nativeQuery =true)
//  List<Integer> listProduct();
  
  @Query(value="SELECT * from cartproduct where product_id=?1" , nativeQuery =true)
  CartProduct productInCartP (Integer id);
  
  @Query(value="SELECT  cart_id from cartproduct where product_id=?1" , nativeQuery =true)
  Integer findIdCart (Integer id);
}
