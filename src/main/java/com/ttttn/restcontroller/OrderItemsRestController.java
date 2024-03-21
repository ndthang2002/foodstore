package com.ttttn.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.dto.OrderAccDto;
import com.ttttn.entity.OrderItems;
import com.ttttn.service.OrderItemService;

@RestController
@RequestMapping("/rest/orderitem")
public class OrderItemsRestController {

  @Autowired
  OrderItemService orderItemService;
  
  @GetMapping("/getallorderitem")
  public List<OrderItems> getall(){
    List<OrderItems> orderItems = orderItemService.findAll();
    return orderItems;
  }
  

  
}
