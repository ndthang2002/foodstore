package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
