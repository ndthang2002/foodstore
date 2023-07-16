package com.ttttn.controller;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.bcel.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ttttn.entity.Account;
import com.ttttn.service.AccountService;
import com.ttttn.utilities.SendMail;

import net.bytebuddy.utility.*;

@Controller
public class ForgotPasswordController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private SendMail sendMail;

  
  @GetMapping("/forgot_password")
  public String showForgotPasswordForm() {
    return "login/forgotpassword";
  }

  @PostMapping("/forgot_password")
  public String processForgotPassword(HttpServletRequest request ,Model model) throws Exception {
    System.out.println("ok");
    String email = request.getParameter("email");
    String token = RandomString.make(30); 

    try {
      accountService.updateResetPasswordToken(token, email);
      String resetPasswordLink = com.ttttn.utilities.Utility.getSiteURL(request)+"/reset_password?token=" + token;
      sendMail.send_Email(email, resetPasswordLink);
  
      model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
       
  } catch (Exception ex) {
      model.addAttribute("error", ex.getMessage());
      System.out.println("loi khi post forgot");
    ex.printStackTrace();
  }
    return "login/forgotpassword";
  }
  
   
  public void sendEmail(){

  }  
   
   
  @GetMapping("/reset_password")
  public String showResetPasswordForm(@Param(value="token") String token,Model model) {
    Account account = accountService.getByResetPasswordToken(token);
    model.addAttribute("token", token);
    if(account ==null) {
      model.addAttribute("message", "invalid token");
      return "message";
    }
    return "login/reset_password_form";
  }
   
  @PostMapping("/reset_password")
  public String processResetPassword(HttpServletRequest request ,Model model) {

    String token = request.getParameter("token");
    String password =request.getParameter("password");
    Account account = accountService.getByResetPasswordToken(token);
    model.addAttribute("title", "reset your password");
    if(account== null)
    {
       model.addAttribute("message", "invalid token");
       return "message";
    }else {
      accountService.updatePassword(account, password);
      model.addAttribute("message", "Bạn đã thay đổi mật khẩu thành công!");
    }
    return "login/login";
    }
}
