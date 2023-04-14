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
//  @Override
//  public List<Integer> ListProduct() {
//    // TODO Auto-generated method stub
//    return cartProductJparepository.listProduct();
//  }
  @Override
  public CartProduct productInCartP(Integer id) {
    // TODO Auto-generated method stub
    return cartProductJparepository.productInCartP(id);
  }
  @Override
  public Integer findIdCart(Integer idP) {
    // TODO Auto-generated method stub
    return cartProductJparepository.findIdCart(idP);
  }
  @Override
  public CartProduct findCartPbyCartid(Integer id) {
    // TODO Auto-generated method stub
    return cartProductJparepository.findCartPbyCartid(id);
  }
  @Override
  public Integer findIdProductByCartid(Integer id) {
    // TODO Auto-generated method stub
    return cartProductJparepository.findIdProductByCartid(id);
  }
  @Override
  public boolean delete(CartProduct cartProduct) {
    // TODO Auto-generated method stub
    try {
      
      cartProductJparepository.delete(cartProduct);
    } catch (Exception e) {
      return false;
      // TODO: handle exception
    }
    return true;
  }

  
 
  
  

}