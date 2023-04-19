package com.ttttn.restcontroller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.Address;
import com.ttttn.entity.Cart;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.DeliveryMethod;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;
import com.ttttn.entity.Product;
import com.ttttn.service.AccountService;
import com.ttttn.service.AddressService;
import com.ttttn.service.CartProductService;
import com.ttttn.service.CartService;
import com.ttttn.service.DeliveryMethodService;
import com.ttttn.service.OrderItemService;
import com.ttttn.service.OrderService;
import com.ttttn.service.ProductService;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
  @Autowired
  SecurityConfig     config;
  @Autowired
  CartService        cartService;
  @Autowired
  AccountService     accountService;
  @Autowired
  AddressService     addressService;
  @Autowired
  OrderService       orderService;
  @PersistenceContext
  EntityManager      entityManager;
  @Autowired
  CartProductService cartProductService;
  @Autowired
  OrderItemService   orderItemService;
  @Autowired
  ProductService     productService;
  @Autowired
  DeliveryMethodService deliveryMethodService;

  @PostMapping("/allproducttocart")
  public String saveOrder(@RequestParam("city_Id") Integer cityid, @RequestParam("district_Id") Integer districtId,
      @RequestParam("ward_Id") Integer wardId, @RequestParam("delivery_name") String deliveryName,
      @RequestParam("delivery_fee") double deliveryFee) {

    /* add dia chi theo user */
    Account account = new Account();
    account = accountService.findbyid(config.accountLogedIn.getUserid());
    Address address = new Address();
    // kiem tra dia chi co chua
    if (addressService.findAddressByUser(account.getUserid())== null) {
      address.setCityid(cityid);
      address.setDistrictid(districtId);
      address.setWardid(wardId);
      address.setUser(account);
      addressService.insert(address);
    } else {
      /*
       * update neu tai khoan da co
       */      
      address.setAddressid(addressService.findAddressByUser(account.getUserid()).getAddressid());
      address.setCityid(cityid);
      address.setDistrictid(districtId);
      address.setWardid(wardId);
      address.setUser(account);
      addressService.insert(address);
    }
    /* add dia chi theo user */

    /* add vao order */
    Order order = new Order();
    // kiem tra cart da duoc order chua
    boolean checkcart = false;

    List<Cart> listCart = cartService.findIdCartByUserid(config.accountLogedIn.getUserid());
    for (Cart listc : listCart) {
      if (listc.getOrder() != null) {
        checkcart = false;
      } else {
        checkcart = true;
      }
    }
    // cart chua co trong order
    if (checkcart) {
      // tinh tong tien
      double tongtien = 0;
      for (Cart listc : listCart) {
        if (listc.getOrder() != null) {
          tongtien = 0;
        } else {
          tongtien = tongtien + listc.getTotalall();
        }
      }
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      String formattedDateTime = now.format(formatter);
      order.setBookingdate(new Date());
      order.setDeliverydate(new Date());
      order.setOrderstatus("đang giao");
      order.setTotalmoney((tongtien * 1000) + deliveryFee);
      order.setUser(account);
      orderService.insert(order);
      
      //add delivery_mothod
      DeliveryMethod deliveryMethod = new DeliveryMethod();
      deliveryMethod.setName(deliveryName);
      deliveryMethod.setPrice(deliveryFee);
      deliveryMethod.setOrder(order);
      deliveryMethodService.insert(deliveryMethod);
      
        //add order_items 
      OrderItems orderItems = new OrderItems();
        for(Cart listcart :listCart) {
          
        Integer idProduct = cartProductService.findIdProductByCartid(listcart.getCartid());
        Product product   = productService.findById(idProduct);
        orderItems.setOrder(order);
        orderItems.setProduct(product);
        orderItems.setQuantity(listcart.getQuantityproduct());
        orderItemService.insert(orderItems);
       }
        
        //xoa het cart sau khi mua 
        for(Cart cart :listCart) {
          CartProduct cartProduct = cartProductService.findCartPbyCartid(cart.getCartid());
          cartProductService.delete(cartProduct);
          cartService.delete(cart);
        }

    }

    return "thanh cong";
  }

}
