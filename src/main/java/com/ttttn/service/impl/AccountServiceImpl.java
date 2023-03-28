package com.ttttn.service.impl;

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
    userJparepository.save(account);
    return null;
  }
}
