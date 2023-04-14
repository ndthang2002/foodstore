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

  @Override
  public Cart insert(Cart cart) {
    // TODO Auto-generated method stub
    return cartJparepository.save(cart);
  }

  @Override
  public Cart findById(Integer id) {
    // TODO Auto-generated method stub
    return cartJparepository.findById(id).get();
  }

  @Override
  public List<Cart> findIdCartByUserid(Integer id) {
    // TODO Auto-generated method stub
    return cartJparepository.findIdCartByUserid(id);
  }

  @Override
  public Integer getCountCart(Integer id) {
    // TODO Auto-generated method stub
    return cartJparepository.getCountCart(id);
  }

  @Override
  public boolean delete(Cart cart) {
    // TODO Auto-generated method stub
    try {
      cartJparepository.delete(cart);
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
    
    return true;
  }

//  @Override
//  public void deletecartbyuser(Integer userid) {
//    // TODO Auto-generated method stub
//    try {
//      cartJparepository.deletecartbyuser(userid);
//    } catch (Exception e) {
//      // TODO: handle exception
//      e.printStackTrace();
//    }
//  }







}