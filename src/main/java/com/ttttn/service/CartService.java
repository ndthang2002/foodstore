package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Cart;

public interface CartService {
  List<Cart> getAllCart();
  Cart findCartById(Integer id);
  
  Cart insert(Cart cart);
  Cart findById(Integer id);
  
  Integer findIdCartByUserid (Integer id);
}
