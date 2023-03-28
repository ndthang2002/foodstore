package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttttn.entity.CartProduct;
import com.ttttn.repository.CartJparepository;
import com.ttttn.repository.CartProductJparepository;
import com.ttttn.service.CartProductService;
import com.ttttn.service.CartService;

@Service
public class CartProductServiceImpl  implements CartProductService{

  @Autowired
  CartProductJparepository cartProductJparepository;
  @Override
  public List<CartProduct> fillAllCart() {
    // TODO Auto-generated method stub
    return cartProductJparepository.findAll();
  }
  @Override
  public CartProduct insert(CartProduct cartProduct) {
    // TODO Auto-generated method stub
    return cartProductJparepository.save(cartProduct);
  }
  
  

}
