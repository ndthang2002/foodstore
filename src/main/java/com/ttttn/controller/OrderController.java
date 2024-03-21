package com.ttttn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {

  @Autowired
  HttpServletRequest req;
  @RequestMapping(value = "/orderdetail")
  public String orderDetail(Model model,
                          @RequestParam("vnp_Amount") String paymentCount,
                          @RequestParam("vnp_CardType") String vnp_CardType,
                          @RequestParam("vnp_BankCode") String vnp_BankCode,
                          @RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
                          @RequestParam("vnp_OrderInfo") String vnp_OrderInfo){
     
          
         
          model.addAttribute("paymentPrice", Integer.parseInt(paymentCount));
          model.addAttribute("vnp_CardType", vnp_CardType);
          model.addAttribute("vnp_BankCode", vnp_BankCode);
          model.addAttribute("vnp_OrderInfo", vnp_OrderInfo);
      
          return "order/orderdetail";
      
  }
  
  @RequestMapping("/donhang")
  public String sfd() {
    return "order/donmua";
  }
  @GetMapping("/orderAccount")
  public String showOrderAccount() {
    return "/order/orderAccounts";
  }
}
