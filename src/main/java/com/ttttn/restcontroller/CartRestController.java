package com.ttttn.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.entity.Cart;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.CartService;
import com.ttttn.service.ProductService;

@CrossOrigin("*")
@RestController
public class CartRestController {

  @Autowired
  ProductService productService;
  @Autowired
  CartService cartService;
  
//  @PostMapping("/rest/addtocart/${id}")
//  public RequestEntity<?> addtocart(@RequestParam("id") Integer id, @RequestParam("soluong")  Integer soluong , @RequestParam("sotien") Double sotien){
//    Product product = productService.findById(id);
//    Cart cart = new Cart();
//    
//    cart.setTotalall(sotien);
//    cart.setQuantityproduct(soluong);
//    CartService.insert(cart);
//    CartProduct cartProduct = new CartProduct();
//    cartProduct.setCart(cart);
//    cartProduct.setProduct(product);
//    
//    
//    return null;
//  }
}
