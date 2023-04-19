package com.ttttn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Authorities;
import com.ttttn.repository.AuthoritiesJparepository;
import com.ttttn.service.AuthoritiesService;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{

  @Autowired
  AuthoritiesJparepository authoritiesJparepository;
  @Override
  public Authorities insert(Authorities authorities) {
    // TODO Auto-generated method stub
    return authoritiesJparepository.save(authorities);
  }
  @Override
  public Integer findIdRoleByUser(Integer id) {
    // TODO Auto-generated method stub
    return  authoritiesJparepository.findIdRoleByUser(id);
  }
  
}
