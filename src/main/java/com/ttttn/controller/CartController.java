package com.ttttn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttttn.entity.Cart;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.CartProductService;
import com.ttttn.service.CartService;
import com.ttttn.service.ProductService;

@Controller
public class CartController {

  @Autowired
  CartProductService cartProductService;
   
    @Autowired 
    ProductService productService;
    
    @Autowired 
    CartService cartService;
   

  @RequestMapping("/cart")
 public String showAll( Model model) {

    List<CartProduct> list = cartProductService.fillAllCart();
    CartProduct cartProduct = list.get(0);
    Product product = cartProduct.getProduct();
    model.addAttribute("product", product);
    return "order/cart";
  }
  @RequestMapping("/payments")
  public String payment() {
    return "order/checkout";
  }
  
  
}
