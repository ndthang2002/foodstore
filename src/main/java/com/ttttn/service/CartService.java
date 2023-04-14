package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Cart;

public interface CartService {
  List<Cart> getAllCart();
  Cart findCartById(Integer id);
  
  Cart insert(Cart cart);
  Cart findById(Integer id);
  
  List<Cart> findIdCartByUserid (Integer id);
  Integer getCountCart(Integer id);
  boolean delete(Cart cart);
//  void deletecartbyuser(Integer userid);
}