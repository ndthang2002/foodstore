package com.ttttn.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ttttn.SecurityConfig;
import com.ttttn.dto.nhanvienDto;
import com.ttttn.entity.Account;
import com.ttttn.entity.AccountAndAddress;
import com.ttttn.entity.Address;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.DeliveryMethod;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;
import com.ttttn.entity.Payment;
import com.ttttn.entity.Role;
import com.ttttn.service.AccountService;
import com.ttttn.service.AddressService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.DeliveryMethodService;
import com.ttttn.service.OrderItemService;
import com.ttttn.service.OrderService;
import com.ttttn.service.PayService;
import com.ttttn.service.RoleService;
import com.ttttn.service.UploadFileService;

@CrossOrigin("*")
@RestController

public class AccountRestController {

  @Autowired
  AccountService       accountService;

  @Autowired
  AddressService       addressService;
  
  @Autowired
  AuthoritiesService   authoritiesService;

  @Autowired
  UploadFileService    uploadFileService;

  @Autowired
  ServletContext       app;

  @Autowired
  RoleService          roleService;
  
  @Autowired
  OrderService orderService;
  @Autowired
  DeliveryMethodService deliveryMethodService;
  @Autowired
  OrderItemService orderItemService;
  @Autowired
  PayService payService;
  
  SecurityConfig       config;

  public static String images;

  @GetMapping("/rest/account/loged/")
  public Account getAcc() {
    Account account = new Account();
    account = accountService.findbyid(12);
    return account;
  }

  // lay ra toan bo vai tro
  @GetMapping("rest/allrole")
  public List<Role> getRoles() {
    return roleService.getAllRole();
  }
  
  // add nhanvien da được thay thế bằng java controller
  /*
   * @PostMapping("/rest/nhanvien/create/{id}") public Account create(@RequestBody
   * AccountAndAddress request, @PathVariable("id") Integer roleid) {
   * 
   * Account account = request.getAccount(); Address address =
   * request.getAddress();
   * 
   * MultipartFile file = request.getFile();
   * System.out.println(file.getOriginalFilename()); System.out.println("nghad");
   * 
   * System.out.println("b3"+AccountRestController.images); account.setImage(
   * AccountRestController.images); Account acc =accountService.insert(account);
   * Address addres = address; addres.setUser(account);
   * addressService.insert(addres); //insert role Authorities authorities = new
   * Authorities();
   * 
   * Role role = roleService.findById(roleid); authorities.setRole(role);
   * authorities.setUser(account); authoritiesService.insert(authorities); return
   * acc;
   * 
   * }
   */
   
  @GetMapping("/rest/nhanvien/getlist")
  public List<Account> getList() {
    List<Account> list = accountService.findAll();

    return list;
  }
  //lay dia chi
  @GetMapping("/rest/nhanvien/getAddress/{id}")
  public Address getListAddress(@PathVariable("id") Integer id)
      {
    Address address = addressService.findAddressByUser(id);
    return address;
  }
  
  //lay quyen
  @GetMapping("/rest/nhanvien/getAuthorities/{id}")
  public Authorities getAtuAuthorities(@PathVariable("id") Integer id) {
    Authorities authorities = authoritiesService.findAuthoritiesByUser(id);
    return authorities;
  }
  
  @DeleteMapping("/rest/nhanvien/deleteAccount/{id}")
  public Account deleteAddress(@PathVariable("id") Integer id)
      {
    //xoa dia chi
    Address address = addressService.findAddressByUser(id);
    System.out.println(address.getAddressid());
    addressService.delete(address);
    
    // xoa phan quyen
    Authorities authorities = authoritiesService.findAuthoritiesByUser(id);
    authoritiesService.delete(authorities);
    
    // xoa order
      
      // xoa order
      if(orderService.findOrderByUser(id) !=null) {
        List<Order> listOrder = orderService.findOrderByUser(id);
        for(Order order :listOrder) {
          //xoa devlvery
          DeliveryMethod deliveryMethod = deliveryMethodService.finDeliveryMethod(order.getOrderid());
          deliveryMethodService.delete(deliveryMethod);
          //xoa order items
          List<OrderItems> orderItems = orderItemService.findOrderItemsbyOrder(order.getOrderid());
          for(OrderItems orderItem :orderItems) {
            orderItemService.delete(orderItem);
          }
          
          List<Payment> payments = payService.findPaymentsbbyOrder(order.getOrderid());
          for(Payment pay :payments) {
            payService.delete(pay);
          }
          orderService.delete(order);
        }
    
    
    //xoa payment
    //xoa order
      
  

  
    }
    Account account = accountService.findbyid(id);
     accountService.delete(id);
    
    return account;
  }
  
  //upload 
  @PostMapping("/rest/nhanvien/update/{idUser}")
  public Account updateAccount(@PathVariable("idUser") Integer idUser,@RequestBody AccountAndAddress request) {
    
    Account account = request.getAccount();
    Address address = request.getAddress();
    Role role = request.getRole();
    account.setUserid(idUser);
    //account.setImage(image.getOriginalFilename());//
    account = accountService.insert(account);
   
    Address adres = new Address();
    Account acc  = accountService.findbyid(idUser);
    
    //cap nhat anh 
  
//    try {
//      String fileName = image.getOriginalFilename();
//      System.out.println(fileName);
//      //lấy tên ảnh 
//      String filePath = "/Users/thang/dev/projects/duantotnghiep/src/main/resources/static/assets/images/" + fileName;
//      // Lưu tệp ảnh vào thư mục
//      File dest = new File(filePath);
//      image.transferTo(dest);
//    }catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    // kiem tra dia chi co chua
    if (addressService.findAddressByUser(acc.getUserid())== null) {
      adres.setCityid(address.getCityid());
      adres.setDistrictid(address.getDistrictid());
      adres.setWardid(address.getWardid());
      adres.setUser(acc);
      addressService.insert(adres);
    } else {
      /*
       * update neu tai khoan da co
       */      
      adres.setAddressid(addressService.findAddressByUser(idUser).getAddressid());
      adres.setCityid(address.getCityid());
      adres.setDistrictid(address.getDistrictid());
      adres.setWardid(address.getWardid());
      adres.setUser(acc);
      addressService.insert(adres);
    }
   
    Authorities authorities = authoritiesService.findAuthoritiesByUser(idUser);
    Role role2 = roleService.findById(role.getRoleid());
    authorities.setRole(role2);
    authoritiesService.insert(authorities);
    return account;
    
    
  }

}
