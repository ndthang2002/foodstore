package com.ttttn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttttn.entity.Category;
import com.ttttn.entity.Product;
import com.ttttn.service.CategoryService;
import com.ttttn.service.ProductService;

@Controller
public class ProductController {
  @Autowired
  ProductService productService;
  @Autowired
  CategoryService categoryService;
  
  @RequestMapping("/product")
  public String product(Model model) {
  List<Product> listProduct = productService.fillAll();
  model.addAttribute("listProduct", listProduct);
  
  List<Category> listCategory = categoryService.findAll();
  model.addAttribute("listCategory", listCategory);
    return "product/products";
  }
  
  @GetMapping("/categoryid")
  public String showProductByCategoryid(Model model , @RequestParam("cid") String id) {
    List<Product> listlistProductbyCategory = productService.findByCategoryId(id);
    model.addAttribute("listProduct", listlistProductbyCategory);
    return "product/products";
  }
  
  @RequestMapping("/detail")
  public String showProductDetail(Model model ,@RequestParam("pid") Integer id) {
    Product product = new Product();
    product = productService.findById(id);
    model.addAttribute("product", product);
    return "product/product-details";
  }
}
