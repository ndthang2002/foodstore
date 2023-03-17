package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Cart;

public interface CartService {
  List<Cart> getAllCart();
  Cart findCartById(Integer id);
  static Cart insert( Cart obj) {
    // TODO Auto-generated method stub
    return null;
  }
}
