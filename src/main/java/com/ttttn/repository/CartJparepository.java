package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Cart;

@Repository
public interface CartJparepository extends JpaRepository<Cart, Integer>{
  @Query(value="SELECT  * from cart where user_id=?1" , nativeQuery =true)
  List<Cart> findIdCartByUserid (Integer id);
  
  @Query(value="SELECT  COUNT(cartid) from cart WHERE user_id =?1 " , nativeQuery =true)
  Integer getCountCart (Integer id);
//  @Query(value="DELETE  FROM  cart  WHERE user_id=?1 " , nativeQuery =true)
//  void deletecartbyuser (Integer id);


}