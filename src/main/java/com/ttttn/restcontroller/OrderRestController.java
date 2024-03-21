package com.ttttn.restcontroller;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.message.ReusableMessage;
import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.Resultset;
import com.ttttn.SecurityConfig;
import com.ttttn.dto.OrderAccDto;
import com.ttttn.entity.Account;
import com.ttttn.entity.Address;
import com.ttttn.entity.Cart;
import com.ttttn.entity.CartProduct;
import com.ttttn.entity.DeliveryMethod;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;
import com.ttttn.entity.Payment;
import com.ttttn.entity.Product;
import com.ttttn.service.AccountService;
import com.ttttn.service.AddressService;
import com.ttttn.service.CartProductService;
import com.ttttn.service.CartService;
import com.ttttn.service.DeliveryMethodService;
import com.ttttn.service.OrderItemService;
import com.ttttn.service.OrderService;
import com.ttttn.service.PayService;
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
  @Autowired
  PayService payService;
  public static Integer amount;
  

  @PostMapping("/allproducttocart")
  public Order saveOrder(@RequestParam("city_Id") Integer cityid, @RequestParam("district_Id") Integer districtId,
      @RequestParam("ward_Id") Integer wardId, @RequestParam("delivery_name") String deliveryName,
      @RequestParam("delivery_fee") double deliveryFee,@RequestParam("payment") String descriptionPay) {

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
          tongtien = tongtien + listc.getTotalall();
        
      }
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      String formattedDateTime = now.format(formatter);
      order.setBookingdate(new Date());
      order.setDeliverydate(new Date());
      order.setOrderstatus(descriptionPay);
      order.setTotalmoney((tongtien * 1000) + deliveryFee);
      order.setUser(account);
      orderService.insert(order);
      amount = (int) ((tongtien*1000)+deliveryFee);
      //add delivery_mothod
      DeliveryMethod deliveryMethod = new DeliveryMethod();
      deliveryMethod.setName(deliveryName);
      deliveryMethod.setPrice(deliveryFee);
      deliveryMethod.setOrder(order);
      deliveryMethodService.insert(deliveryMethod);
      
      //add order_items 
      
      List<Product> products = new ArrayList<>();
      List<Cart> listCarts = cartService.findIdCartByUserid(config.accountLogedIn.getUserid());
        for(int i=0; i<=listCarts.size()-1;i++) {
        Integer idProduct = cartProductService.findIdProductByCartid(listCarts.get(i).getCartid());
        Product product   = productService.findById(idProduct);
       OrderItems orderItems = new OrderItems();
        
        orderItems.setOrder(order);
        orderItems.setProduct(product);
        orderItems.setQuantityproduct(listCarts.get(i).getQuantityproduct()); 
        orderItemService.insert(orderItems);
        System.out.println(listCarts.get(i).getQuantityproduct());
       
//        update số lượng khi sp bị mua 
        Integer soluong = product.getQuantity() - listCarts.get(i).getQuantityproduct();
        
        product.setQuantity(soluong);
        productService.insert(product);
       }
       
        
        //xoa het cart sau khi mua 
        for(Cart cart :listCart) {
          CartProduct cartProduct = cartProductService.findCartPbyCartid(cart.getCartid());
          cartProductService.delete(cartProduct);
          cartService.delete(cart);
        }
        //add vao payment xac dinh phuong thuc thanh toan 
        Payment payment = new Payment();  
        payment.setAmount(amount);
        payment.setBankcode("");
        payment.setDescription(descriptionPay);
        payment.setOrder(order);
        payService.insert(payment); 
    }
    return order;
  }
  
  
  @GetMapping("/getAllOrder")
  public List<Order> getOrder(){
    List<Order> lists  = orderService.findAll();
    return lists;
  }
  
  @GetMapping("/getOrderdetail/{id}")
  public OrderItems getdetail(@PathVariable("id") Integer id) {
    OrderItems orderitem = new OrderItems();
    orderitem = orderItemService.findOrderItemsbyOrder(id);
    System.out.println(id);
    return orderitem;
  }
  
  @GetMapping("/orderAcc")
  public List<OrderAccDto> getOrderAcc() throws SQLException{
    List<OrderAccDto> listOrderAcc = new ArrayList<>();
    String query =" select * from order_items inner join orders on orders.orderid = order_items.order_id WHERE  user_id="+config.accountLogedIn.getUserid();
   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshop", "root","");
   PreparedStatement statement = connection.prepareStatement(query);
   ResultSet resultSet = statement.executeQuery();
   while(resultSet.next()){
    OrderAccDto orderAccDto = new OrderAccDto();
    // lay quantity sanpham mua 
    orderAccDto.setQuantityProduct(resultSet.getInt("quantityproduct"));
    //get product
    Product product = productService.findById(resultSet.getInt("product_id"));
    orderAccDto.setProduct(product);
    // lay ngay dat
    orderAccDto.setBookingDate(resultSet.getDate("bookingdate"));
    orderAccDto.setDeliveryDate(resultSet.getDate("deliverydate"));
    orderAccDto.setOrderStatus(resultSet.getString("orderstatus"));
    orderAccDto.setTotalMoney(resultSet.getDouble("totalmoney"));
    Account account = accountService.findbyid(resultSet.getInt("user_id"));
    orderAccDto.setUser(account);
    listOrderAcc.add(orderAccDto);
//     
   }
  return listOrderAcc;
  }
}
