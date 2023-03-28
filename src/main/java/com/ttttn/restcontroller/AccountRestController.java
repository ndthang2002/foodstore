package com.ttttn.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttttn.entity.Account;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Role;
import com.ttttn.repository.RoleJparepository;
import com.ttttn.service.AccountService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.RoleService;

@RestController
public class AccountRestController {

}
