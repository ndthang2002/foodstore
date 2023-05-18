
package com.ttttn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Role;
import com.ttttn.service.AccountService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.RoleService;

@Controller
public class AccountController {
  SecurityConfig     acc;
  @Autowired
  AccountService     accService;
  @Autowired
  JavaMailSender     mailer;
  @Autowired
  AuthoritiesService authoritiesService;
  @Autowired
  RoleService        roleService;
  @Autowired
  SecurityConfig     config;

  @PostMapping("/signup")
  public String dangky(Model model, @RequestParam("username") String username, @RequestParam("email") String email,
      @RequestParam("password") String password, @RequestParam("fullname") String fullname) {
    if (accService.findAccountByUserName(username) != null) {
      model.addAttribute("errorsignup", "Tài Khoản đã tồn tại");
      return "login/login";
    }
//   else if(password.equals(password)) {
//     model.addAttribute("message", "Mật khẩu xác nhận lại không chính xác");
//     return "that bai";
//   }
    else {
      System.out.println("b1");
      String hasPass = config.passwordEncoder().encode(password);
      System.out.println(hasPass);
      System.out.println("b2");
      Boolean be = config.passwordEncoder().matches(password, hasPass);
      System.out.println(be);
      
      Account account = new Account();
      account.setUsername(username);
      account.setPassword(hasPass);
      account.setEmail(email);
      account.setName(fullname);
      accService.insert(account);
      Authorities authorities = new Authorities();
      Role role = roleService.findbyname("user");
      authorities.setRole(role);
      authorities.setUser(account);
      authoritiesService.insert(authorities);
      System.out.println("vo day va thanh cong");
    }
    return "login/login";

  }

  @RequestMapping("/login")
  public String loginsucess(Model model) {

//  model.addAttribute("disable", "false");
    
    model.addAttribute("message", "Đăng nhập để trải nghiệm nhiều hơn");
    return "login/login";
    
  }

  @RequestMapping("failureLogin")
  public String loginErorr(Model model) {
    model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác");
    return "login/login";
  }

  @RequestMapping("/logout")
  public String logoff(Model model) {
    model.addAttribute("message", "Đăng xuất thành công");
    this.acc.nameAccount = null;
    this.config.accountLogedIn = null;
    return "login/login";
  }

  @RequestMapping("/forgotpassword")
  public String forgotpassword(Model model, @RequestParam("username") String username,
      @RequestParam("email") String email) {
    Account account = accService.findAccountByUserName(username);
    if (account == null) {
      model.addAttribute("message", "Tài khoản không tồn tại");
    } else if (!account.getEmail().equals(email)) {
      System.out.println("day laa" + account.getEmail());
      model.addAttribute("message", "Sai email liên kết với tài khoản");
    } else {
      try {
        MimeMessage mail = mailer.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setFrom("nguyendinhthang23082002@gmail.com");
        helper.setTo(email);
        helper.setReplyTo("nguyendinhthang23082002@gmail.com");
        helper.setSubject("Chào bạn " + account.getName() + "");
        helper.setText("Mật khẩu của bạn là:  " + account.getPassword(),
            true + "vui lòng không cung cấp mật khẩu này cho ai");
        // gui mail
        mailer.send(mail);
        model.addAttribute("message", "vui lòng check mail để nhận mật khẩu");
      } catch (Exception e) {
        e.printStackTrace();
        // TODO: handle exception
        model.addAttribute("message", "lỗi gửi mail vui lòng kiểm tra lại");
      }
    }
    return "login/login";
  }

  // vao trang edit taikhoan
  @RequestMapping("/editaccount")
  public String editacc() {
    return "login/account";
  }

  @PostMapping("/accountedit")
  public String editAccount(
      @RequestParam("username") String username, 
      @RequestParam("password") String password,
      @RequestParam("email") String email, 
      @RequestParam("phone") String phone, 
      @RequestParam("name") String name,
      @RequestParam("image") MultipartFile image
      ,Model model) {
    
  
    Account account = new Account();
    account.setUserid(config.accountLogedIn.getUserid());
    account.setUsername(username);
    account.setPassword(password);
    account.setEmail(email);
    account.setPhone(phone);
    account.setName(name);
    
    // cap nhat anh 
    // up anh len clound va vao database
    Map<String, String> config = new HashMap<>();
    config.put("cloud_name", "dpbixmrep");
    config.put("api_key", "132427124622788");
    config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");

    Cloudinary cloudinary = new Cloudinary(config);

    Map params = ObjectUtils.asMap("upload_preset", "v9e7akio");
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
    } else {
      System.out.println("anh 1 null");
    }
    
    
    accService.insert(account);
    model.addAttribute("thongbao", "Cập nhật thành công");
    return "login/account";
  }
  // thay doi dia chi
//  @PostMapping("/changeadress")
//  public String changeAdress(@RequestParam("city") String city,@RequestParam("district") String district,@RequestParam("ward") String ward
//      ) {
//    if(config.accountLogedIn==null) {
//      return null;
//    }
//    Account account = accService.findbyid(config.accountLogedIn.getUserid());
//    String address= ward+district+city;
//    account.setAddress(address);
//  
//    accService.insert(account);
//    return "redirect:/payments";
//  }
  


}