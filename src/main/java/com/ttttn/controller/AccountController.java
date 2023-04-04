package com.ttttn.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Role;
import com.ttttn.service.AccountService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.RoleService;

@Controller
public class AccountController {
SecurityConfig acc;
  @Autowired
  AccountService userService;
  @Autowired
  JavaMailSender mailer;
  
  @Autowired
  AuthoritiesService authoritiesService;
  @Autowired
  RoleService roleService;
  
  SecurityConfig config;
  
  
 @PostMapping("/signup")
 public String dangky(Model model,@RequestParam("username") String username,@RequestParam("email") String email,@RequestParam("password") String password ,@RequestParam("fullname") String fullname ){
   if(userService.findAccountByUserName(username)!=null) {
     model.addAttribute("errorsignup", "Tài Khoản đã tồn tại");
     return "login/login";
   }
//   else if(password.equals(password)) {
//     model.addAttribute("message", "Mật khẩu xác nhận lại không chính xác");
//     return "that bai";
//   }
   else {
     Account account = new Account();
     account.setUsername(username);
     account.setPassword(password);
     account.setEmail(email);
     account.setName(fullname);
     userService.insert(account);
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
   if(config.isLogedIn==true) {
     System.out.println("co vo day rou");
     return "redirect:/index";
   }
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
    return "login/login";
  }
  
  @RequestMapping("/forgotpassword")
  public String forgotpassword(Model model,@RequestParam("username") String username,@RequestParam("email") String email) {
    Account account = userService.findAccountByUserName(username);
    if(account==null) {
      model.addAttribute("message", "Tài khoản không tồn tại");
    }else if(!account.getEmail().equals(email)) {
      System.out.println("day laa"+account.getEmail());
        model.addAttribute("message","Sai email liên kết với tài khoản");
    }
      else {
       try {
         MimeMessage mail = mailer.createMimeMessage();
        MimeMessageHelper  helper = new MimeMessageHelper(mail);
        helper.setFrom("nguyendinhthang23082002@gmail.com");
        helper.setTo(email);
        helper.setReplyTo("nguyendinhthang23082002@gmail.com");
        helper.setSubject("Chào bạn "+account.getName() + "");
        helper.setText("Mật khẩu của bạn là:  " +account.getPassword(),true+"vui lòng không cung cấp mật khẩu này cho ai");
        //gui mail
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
  

  
}
