package com.ttttn.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.SecurityConfig;
import com.ttttn.dto.CartDto;
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

  SecurityConfig     config;

//  // luu san pham vao gio hang
  @GetMapping("/rest/addtocart/{id}")
  //@ResponseBody
  public CartDto addtocart(@PathVariable("id") Integer id) {
   
    // dinh dang ngay gio
    // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Account user;
    Product product = productService.findById(id);
    Cart cart = new Cart();
    //sau khi them vao gio hang se tra ve mot cartdto
    CartDto cartDto = new CartDto();
    try {
      System.out.println(config.accountLogedIn.getUserid());
      int quantity = 1;
      if (cartProductService.productInCartP(id) == null) {
        
        //add san pham vao gio hang
        
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
        //update lai gio neu da co san pham trong gio hang
        cart = cartService.findById(cartProductService.findIdCart(id));
        quantity = cart.getQuantityproduct();
        quantity = quantity + 1;
        cart.setQuantityproduct(quantity);
        user = userService.findbyid(config.accountLogedIn.getUserid());
        cart.setUser(user);
        cart.setTotalall(quantity * product.getPrice());
        cart.setDatecreated(new Date());
        cartService.insert(cart);
        return cartDto;
      }

      //tra ve mot doi tuong cartDTO)
      cartDto.setCartid(cart.getCartid());
      cartDto.setDesciption(product.getModel());
      cartDto.setImageproduct(product.getImage());
      cartDto.setNameproduct(product.getName());
      cartDto.setPrice(product.getPrice());
      cartDto.setProductid(product.getProductid());
      cartDto.setQuantity(cart.getQuantityproduct());
      cartDto.setTotal(cart.getTotalall());
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return cartDto;
  }

//  lấy giỏ hàng theo từng tài khoản
  @GetMapping("/rest/getCartByUserLoged")
  public List<CartDto> getCartDB() {
// Cart cart =cartService.findById( cartService.findIdCartByUserid(id));
    
//     lay userid cua tai khoan dang dang nhap vao
    if(config.accountLogedIn.getUserid()==null) {
      
      List<CartDto> CartNull= new ArrayList<>();
      return CartNull;
    }
    Integer id = config.accountLogedIn.getUserid();
    List<CartDto> listCartDTO = new ArrayList<CartDto>();
      
      
    List<Cart> listcart = cartService.findIdCartByUserid(id);
   
    
    for (Cart listCart : listcart) {
      CartDto cartDto = new CartDto();
      CartProduct cartProduct = cartProductService.findCartPbyCartid(listCart.getCartid());
      Product product = productService.findById(cartProductService.findIdProductByCartid(listCart.getCartid()));
      cartDto.setProductid(product.getProductid());
      cartDto.setCartid(listCart.getCartid());
      cartDto.setImageproduct(product.getImage());
      cartDto.setNameproduct(product.getName());
      cartDto.setPrice(product.getPrice());
      cartDto.setQuantity(listCart.getQuantityproduct());
      cartDto.setTotal(listCart.getTotalall());
      cartDto.setDesciption(product.getModel());
      listCartDTO.add(cartDto);
    }
   

    return listCartDTO;
  }
//  //dem so luong cac san pham 
////  @GetMapping("rest/getcountcart")
////  public Integer getcount() {
////    Integer id = config.accountLogedIn.getUserid();
////    Integer quantityCart = cartService.getCountCart (id);
////    System.out.println("thang");
////    return quantityCart;
////  }
////  
//  
//  //kiem tra dang nhap
  @GetMapping("/rest/checklogin")
  public boolean checkLogin() {
    boolean loged = false ;
    if(config.accountLogedIn!=null) {
      loged=true;
    }
    return loged;
  }

  //xoa san pham trong gio hang
  @DeleteMapping("/rest/deleteCart/{id}")
  public boolean deleteCart(@PathVariable("id") Integer id) {
    boolean deleted=false;
    try {
      CartProduct cartProduct = cartProductService.findCartPbyCartid(id);
     Cart cart = cartService.findById(id);
     cartProductService.delete(cartProduct);
     cartService.delete(cart);
     deleted=true;
    } catch (Exception e) {
      deleted=false;
      // TODO: handle exception
    }
     return deleted;
  }

///xoa toan bo san pham trong gio hang
  @DeleteMapping("/rest/deletecartall")
  public boolean deleteall() {
    if(config.accountLogedIn==null) {
      return false;
    }
    else {
      List<Cart> listCart = cartService.findIdCartByUserid(config.accountLogedIn.getUserid());
      System.out.println(listCart.size());
      for(Cart cart: listCart) {
        CartProduct cartProduct = cartProductService.findCartPbyCartid(cart.getCartid());
        cartProductService.delete(cartProduct);
        cartService.delete(cart);
      }
    }
   
    return true;
  }
}
