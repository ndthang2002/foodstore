package com.ttttn.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.entity.Cart;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.Product;
import com.ttttn.entity.Account;
import com.ttttn.repository.CartJparepository;
import com.ttttn.service.CartProductService;
import com.ttttn.service.CartService;
import com.ttttn.service.ProductService;
import com.ttttn.service.AccountService;
//
@CrossOrigin(origins="*")
@RestController
public class CartRestController {

  @Autowired
  ProductService productService;
  @Autowired
  CartService cartService;
  @Autowired
  AccountService userService;
  @Autowired
  CartProductService cartProductService;
  
  @PostMapping("/rest/addtocart/{id}")
  @ResponseBody
  public String addtocart(@PathVariable("id") Integer id) {
    //dinh dang ngay gio 
//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    try {
    System.out.println(id);
    int quantity =1;
    Product product = productService.findById(id);
    Cart cart = new Cart();
    cart.setQuantityproduct(quantity);
    Account user = userService.findbyid(1);
    cart.setUser(user);
    cart.setTotalall(quantity*product.getPrice());
    cart.setDatecreated(new Date());
    cartService.insert(cart);
    //them vao bang cartproduct
    CartProduct cartProduct = new CartProduct();
    Cart cartitem = cartService.findById(cart.getCartid());
    cartProduct.setCart(cartitem);
    cartProduct.setProduct(product);
    cartProductService.insert(cartProduct);
    
  } catch (Exception e) {
    // TODO: handle exception
    e.printStackTrace();
  }
     return "thanhcong";
  }
  @PostMapping("/rest/thi")
  @ResponseBody
  public String ji() {  
    return  "thang";
  }
}
