package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.CartProduct;
@Repository
public interface CartProductJparepository  extends JpaRepository<CartProduct, Integer>{

//  @Query(value="SELECT product_id from cartproduct" , nativeQuery =true)
//  List<Integer> listProduct();
  
  @Query(value="SELECT * from cartproduct where product_id=?1" , nativeQuery =true)
  CartProduct productInCartP (Integer id);
  
  @Query(value="SELECT  cart_id from cartproduct where product_id=?1" , nativeQuery =true)
  Integer findIdCart (Integer id);
  
  @Query(value="SELECT  * from cartproduct where cart_id=?1" , nativeQuery =true)
  CartProduct findCartPbyCartid (Integer id);
  
  @Query(value="SELECT  product_id from cartproduct where cart_id=?1" , nativeQuery =true)
  Integer findIdProductByCartid (Integer id);

//  @Query(value="delete  from CartProduct cp  WHERE cp.cart.cartid=?1")
//   void deleteCPbycartid (Integer id);
  
}