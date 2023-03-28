package com.ttttn.service;

import com.ttttn.entity.Account;

public interface AccountService {
   Account findbyid(Integer id);
   Account findAccountByUserName(String username);
   Account insert(Account account);
}
