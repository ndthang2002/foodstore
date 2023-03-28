package com.ttttn.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Role;
import com.ttttn.repository.RoleJparepository;
import com.ttttn.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

  @Autowired
  RoleJparepository roleJparepository;
  @Override
  public Role findbyname(String role) {
    // TODO Auto-generated method stub
    return roleJparepository.findByKeyword(role);
  }

}
