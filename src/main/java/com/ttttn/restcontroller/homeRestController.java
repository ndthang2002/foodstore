package com.ttttn.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.entity.Account;
import com.ttttn.entity.Order;
import com.ttttn.entity.Product;
import com.ttttn.service.AccountService;
import com.ttttn.service.OrderService;
import com.ttttn.service.ProductService;

@CrossOrigin("*")
@RestController
public class homeRestController {

  @Autowired
  OrderService orderService;
  @Autowired
  AccountService accountService;
  @Autowired
  ProductService productService;
  
  @GetMapping("/rest/home/sodonhang")
  public Integer getSLDonHang() {
    int soluong =0;
    for(Order order : orderService.findAll()) {
      soluong =soluong+1;
    }
    return soluong;
  }
  
  @GetMapping("/rest/home/sonhanvien")
  public Integer getSLNhanVien() {
    int soluong =0;
    for(Account order : accountService.findAll()) {
      soluong =soluong+1;
    }
    return soluong;
  }
  
  @GetMapping("/rest/home/sosanpham")
  public Integer getSLSanPham(){
    int soluong =0;
    for(Product order : productService.fillAll()) {
      soluong =soluong+1;
    }
    return soluong;
  }
}
