package com.ttttn.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import com.ttttn.entity.Product;
import com.ttttn.service.ProductService;

@CrossOrigin("*")
@RestController
public class RestProductController {
  @Autowired
  ProductService productService;
  
  
  @GetMapping("/rest/products/{id}")
  @ResponseBody 
  public Product getProduct( @PathVariable("id") Integer id) {
  
    return productService.findById(id);
   
  }
  
  @GetMapping("/rest/product")
  public List<Product> getListProduct(){
    return productService.fillAll();
  }
}
