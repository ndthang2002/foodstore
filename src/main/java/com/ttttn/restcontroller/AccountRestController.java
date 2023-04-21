package com.ttttn.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Role;
import com.ttttn.repository.RoleJparepository;
import com.ttttn.service.AccountService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.RoleService;
@CrossOrigin("*")
@RestController

public class AccountRestController {
  
  @Autowired
  AccountService accountService;
  
  SecurityConfig config;
  
  @GetMapping("/rest/account/loged/")
  public Account getAcc() {
      
      Account account = new Account();
      account = accountService.findbyid(12);
      
    return account;
  }
}
