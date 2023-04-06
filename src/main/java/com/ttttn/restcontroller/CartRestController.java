package com.ttttn.restcontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.Cart;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.AccountService;
import com.ttttn.service.CartProductService;
import com.ttttn.service.CartService;
import com.ttttn.service.ProductService;

//
@CrossOrigin("*")
@RestController
public class CartRestController {

  @Autowired
  ProductService     productService;

  @Autowired
  CartService        cartService;

  @Autowired
  AccountService     userService;

  @Autowired
  CartProductService cartProductService;

  SecurityConfig config;

  @GetMapping("/rest/addtocart/{id}")
  @ResponseBody
  public String addtocart(@PathVariable("id") Integer id) {
    System.out.println("thnaf");
    // dinh dang ngay gio
    // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Account user;
    Product product = productService.findById(id);
    try {
      System.out.println(config.accountLogedIn.getUserid());
      int quantity = 1;
      if (cartProductService.productInCartP(id) == null) {
        
        Cart cart = new Cart();
        cart.setQuantityproduct(quantity);
        user = userService.findbyid(config.accountLogedIn.getUserid());
        cart.setUser(user);
        cart.setTotalall(quantity * product.getPrice());
        cart.setDatecreated(new Date());
        cartService.insert(cart);
        // them vao bang cartproduct
        CartProduct cartProduct = new CartProduct();
        Cart cartitem = cartService.findById(cart.getCartid());
        cartProduct.setCart(cartitem);
        cartProduct.setProduct(product);
        cartProductService.insert(cartProduct);

      } else {
        
        Cart cart = cartService.findById(cartProductService.findIdCart(id));
        quantity = cart.getQuantityproduct();
        quantity = quantity + 1;
        cart.setQuantityproduct(quantity);
        user = userService.findbyid(config.accountLogedIn.getUserid());
        cart.setUser(user);
        cart.setTotalall(quantity * product.getPrice());
        cart.setDatecreated(new Date());
        cartService.insert(cart);
        
        return "da co";
      }

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return "thanhcong";
  }

//  lấy giỏ hàng theo từng tài khoản
//   @GetMapping("/rest/getcart/{userid}")
//   public CartDto getCartDB(@PathVariable("userid") Integer id) {
//     CartDto cartDto = null ;
//     Cart cart =cartService.findById( cartService.findIdCartByUserid(id));
//     CartProduct cartProduct = cartProductService.findIdCart(id);
//     return cartDto;
//   }

}
