package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Account;
import com.ttttn.repository.AccountJparepository;
import com.ttttn.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{

  @Autowired
  AccountJparepository userJparepository;
  @Override
  public Account findbyid(Integer id) {
    // TODO Auto-generated method stub
    return userJparepository.findById(id).get();
  }
  @Override
  public Account findAccountByUserName(String username) {
    // TODO Auto-generated method stub
    return userJparepository.findAccountByUserName(username);
  }
  @Override
  public Account insert(Account account) {
    // TODO Auto-generated method stub
   
    return  userJparepository.save(account);
  }
  @Override
  public List<Account> findAll() {
    // TODO Auto-generated method stub
    return userJparepository.findAll();
  }
  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub
    userJparepository.deleteById(id);
    
  }
  @Override
  public void updateResetPasswordToken(String token, String email){
    try {
      Account account = userJparepository.findByEmail(email);
      if(account !=null) {
        account.setResetPasswordToken(token);
        userJparepository.save(account);
      }else {
        System.out.println("khoong tim thay nguoi dung");
      }
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println("ngoai le do tim kiem user ");
      e.printStackTrace();
    }
    // TODO Auto-generated method stub
    
  }
  @Override
  public Account getByResetPasswordToken(String token) {
    // TODO Auto-generated method stub
    return userJparepository.findByResetPasswordToken(token);
  }
  @Override
  public void updatePassword(Account account, String newPassword) {
    // TODO Auto-generated method stub
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodePassword = passwordEncoder.encode(newPassword);
    account.setPassword(encodePassword);
    account.setResetPasswordToken(null);userJparepository.save(account);
  }
}
