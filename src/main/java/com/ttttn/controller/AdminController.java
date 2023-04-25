package com.ttttn.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.Address;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Role;
import com.ttttn.restcontroller.AccountRestController;
import com.ttttn.service.AccountService;
import com.ttttn.service.AddressService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.RoleService;

@Controller
@RequestMapping("admin/")
public class AdminController {
  @Autowired
  AccountService accountService;
  @Autowired
  AddressService addressService;
  @Autowired
  AuthoritiesService authoritiesService;
  @Autowired
  RoleService roleService;
  SecurityConfig config;
  @RequestMapping("/")
  public String getIndex(Model model) {
    model.addAttribute("account", config.accountLogedIn);
    return "admin/index";
  }
  
  @RequestMapping("nhanvien")
  public String getNhanvien(Model model) {
    model.addAttribute("account", config.accountLogedIn);
    return "admin/nhanvien/nhanvien";
  }
  @RequestMapping("sanpham")
  public String getSanpham(Model  model) {
    model.addAttribute("account", config.accountLogedIn);
    return "admin/sanpham/sanpham";
  }
  
  @RequestMapping("donhang")
  public String getDonhang(Model model) {
    model.addAttribute("account",config.accountLogedIn);
    return "admin/donhang";
  }
  
  @PostMapping("saveImage")
  public String saveimage(
      @RequestParam("image") MultipartFile image,
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      @RequestParam("role") int  roleid,
      @RequestParam("fullname") String fullname,
      @RequestParam("email") String email,
      @RequestParam("city") Integer city,
      @RequestParam("district") Integer district,
      @RequestParam("ward") Integer ward,
      @RequestParam("phone") String phone,Model model
      ) throws IOException{
    System.out.println(image);
    System.out.println(username);
    System.out.println(password);
    System.out.println(roleid);
    System.out.println(fullname);
    System.out.println(email);
    System.out.println(city);
    System.out.println(district);
    System.out.println(ward);
    System.out.println(phone);
    System.out.println();
      if(username.length()==0) {
        model.addAttribute("errorU", "vui lòng không để trống");
      }
    try {
      
   
      
      
      Account account = new Account();
      account.setUsername(username);
      account.setPassword(password);
     
      
      //up anh len clound va vao database
      Map<String, String> config = new HashMap<>();
      config.put("cloud_name", "dpbixmrep");
      config.put("api_key", "132427124622788");
      config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");

      Cloudinary cloudinary = new Cloudinary(config);
    
      Map params = ObjectUtils.asMap(
           // Nếu trùng tên cũ sẽ ghi đè 
        
          "upload_preset", "v9e7akio"
      );
      if (!image.isEmpty()) {
        try {
          Object res = cloudinary.uploader().upload(image.getBytes(), params);
          // URL để lưu vào database
          String urlUploaded = ((Map<String, String>) res).get("url");
          String baseUrl = "http://res.cloudinary.com/dpbixmrep/image/upload/";
          String path = urlUploaded.replace(baseUrl, "");
          account.setImage(path);
          
        } catch (IOException exception) {
          exception.printStackTrace();
        }
      }
      
      account.setName(fullname);
      account.setEmail(email);
      account.setPhone(phone);
      accountService.insert(account);
      Address address = new Address();
      address.setUser(account);
      address.setCityid(city);
      address.setDistrictid(district);
      address.setWardid(ward);
      addressService.insert(address);
      Authorities authorities = new Authorities();
      Role role = new Role();
      role = roleService.findById(roleid);
      authorities.setRole(role);
      authorities.setUser(account); 
      authoritiesService.insert(authorities);
      System.out.println("sdfs");
      return "redirect:nhanvien";
    } catch (Exception e) {
      e.printStackTrace();
      // TODO: handle exception
      e.printStackTrace();
      System.out.println("loi luu file");
    }
    return "redirect:nhanvien";
    
  }
}
