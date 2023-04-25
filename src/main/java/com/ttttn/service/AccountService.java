package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Account;

public interface AccountService {
   Account findbyid(Integer id);
   Account findAccountByUserName(String username);
   Account insert(Account account);
   List<Account> findAll();
   void delete(Integer id);
}
