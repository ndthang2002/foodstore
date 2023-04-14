package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.CartProduct;

public interface CartProductService {

  List<CartProduct> fillAllCart();
  CartProduct insert(CartProduct cartProduct);
  
//  List<Integer> ListProduct();
  
  CartProduct productInCartP(Integer id);
  Integer findIdCart(Integer idP);
  CartProduct findCartPbyCartid(Integer id);
  Integer findIdProductByCartid(Integer id);
  boolean delete(CartProduct cartProduct);

}