package com.ttttn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttttn.SecurityConfig;
import com.ttttn.dto.DonHang;
import com.ttttn.entity.Account;
import com.ttttn.entity.Order;
import com.ttttn.entity.OrderItems;
import com.ttttn.service.AccountService;
import com.ttttn.service.OrderItemService;
import com.ttttn.service.OrderService;
import com.ttttn.utilities.WriteExcel;

@Controller
public class HandleExcelController {

  @Autowired
  WriteExcel writeExcel;
  @Autowired
  OrderService orderService;
  @Autowired
  OrderItemService orderItemService;
  @Autowired
  AccountService accountService;
  @Autowired
  SecurityConfig config;


  @RequestMapping("/hoadon")
  public String xuatHoaDon(Model model) throws IOException {
    final String excelFilePath ="/Users/thang/dev/projects/duantotnghiep/src/listdonhang/donhang.xlsx";
    List<Order> orders = orderService.findAll();
    List<DonHang>  donhangs = new ArrayList<>();
    OrderItems orderItems = new OrderItems();
    Account account = new Account();
    System.out.println(orders.size());
    for(Order order : orders) {
      DonHang donHang = new DonHang();
        donHang.setMaDonHang(order.getOrderid());
        donHang.setNgayDat(order.getBookingdate());
//      orderItems = orderItemService.findOrderItemsbyOrder(order.getOrderid());
//      donHang.setSoLuongSP(orderItems.getQuantityproduct());
        // lay ten
        account = order.getUser();
        donHang.setTenKhachHang(account.getName());
        donHang.setTongTien(order.getTotalmoney());
        donHang.setTrangThai(order.getOrderstatus());
        System.out.println(donHang.getMaDonHang());
        donhangs.add(donHang);
    }
   
    writeExcel.writeExcel(donhangs,excelFilePath);
    
    model.addAttribute("account",config.accountLogedIn);
    return "admin/donhang";
  }
}
