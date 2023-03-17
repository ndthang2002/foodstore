package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Cart;
import com.ttttn.repository.CartJparepository;
import com.ttttn.service.CartService;
@Service
public class CartServiceImpl implements CartService {

  @Autowired
  CartJparepository cartJparepository;
  
  @Override
  public List<Cart> getAllCart() {
    // TODO Auto-generated method stub
    return cartJparepository.findAll();
  }

  @Override
  public Cart findCartById(Integer id) {
    // TODO Auto-generated method stub
    return cartJparepository.getById(id);
  }

  public Cart insert(Cart obj) {
    // TODO Auto-generated method stub
    cartJparepository.save(obj);
    return null;
  } 

}
