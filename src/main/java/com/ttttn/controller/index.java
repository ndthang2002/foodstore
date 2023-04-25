package com.ttttn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.Category;
import com.ttttn.entity.Comment;
import com.ttttn.entity.Product;
import com.ttttn.entity.Role;
import com.ttttn.repository.CategoryJparepository;
import com.ttttn.repository.ProductJparepository;
import com.ttttn.service.AccountService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.CategoryService;
import com.ttttn.service.RoleService;

@Controller
public class index {

  @Autowired
  CategoryService categoryService;
  
  @Autowired
  AccountService accService;
  @Autowired
  JavaMailSender mailer;
  
  @Autowired
  AuthoritiesService authoritiesService;
  @Autowired
  RoleService roleService;
  
  SecurityConfig config;
  SecurityConfig acc;
  @RequestMapping("/index")
  public String index(Model model)  {
    
    if(config.accountLogedIn==null) {
      return "layout/home";
    }
    int roleid = authoritiesService.findIdRoleByUser(config.accountLogedIn.getUserid());
    Role role = roleService.findById(roleid);
    if(role.getName().equalsIgnoreCase("admin")) {
      System.out.println("vao trang admin");
      return "redirect:admin/";
    }
    
     List<Category> list = categoryService.findAll();
     model.addAttribute("listcategory", list);
//         System.out.println(acc.nameAccount);
//     model.addAttribute("account",acc.nameAccount);
    return "layout/home";
  }
  @RequestMapping("/lienhe")
  public String get() {
    return "blog/contact";
  }
  @RequestMapping("/blog")
  public String blog(Model model) {
    return "/blog/blog";
  }
  
  @RequestMapping("/blog-detail")
  public String blogDetail(Model model) {
    return "/blog/blog-details";
  }
  
  @RequestMapping("/contact")
  public String contact(Model model) {
    return "/blog/contact";
  }
  
  @RequestMapping("/blog-detail1")
  public String blogDetail1(Model model) {
    return "/blog/blog-details1";
  }
  
  @RequestMapping("/blog-detail2")
  public String blogDetail2(Model model) {
    return "/blog/blog-details2";
  }
}
