package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Account;

public interface AccountService {
   Account findbyid(Integer id);
   Account findAccountByUserName(String username);
   Account insert(Account account);
   List<Account> findAll();
   void delete(Integer id);
   //forgot pass
   void updateResetPasswordToken(String token,String email) ;
   Account getByResetPasswordToken(String token);
   void updatePassword(Account account,String newPassword);
   
}
