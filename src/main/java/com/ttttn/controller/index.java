package com.ttttn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.entity.Category;
import com.ttttn.entity.Comment;
import com.ttttn.entity.Product;
import com.ttttn.repository.CategoryJparepository;
import com.ttttn.repository.ProductJparepository;
import com.ttttn.service.CategoryService;

@Controller
public class index {

  @Autowired
  CategoryService categoryService;

  @RequestMapping("/index")
  public String index(Model model)  { 
     List<Category> list = categoryService.findAll();
     model.addAttribute("listcategory", list);
    return "layout/home";
  }
  
}
