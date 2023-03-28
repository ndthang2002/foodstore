package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.CartProduct;

public interface CartProductService {

  List<CartProduct> fillAllCart();
  CartProduct insert(CartProduct cartProduct);
}
