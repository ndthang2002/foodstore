package com.ttttn.service.impl;

import java.util.List;

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
  @Override
  public Authorities findAuthoritiesByUser(Integer idUser) {
    // TODO Auto-generated method stub
    Authorities authorities = null ;
    try {
      authorities = authoritiesJparepository.findAuthoritiesByUser(idUser);
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println("khong tim thay quyen cua user");
    }
     return authorities;
}
  @Override
  public void delete(Authorities authorities) {
    // TODO Auto-generated method stub
    authoritiesJparepository.delete(authorities);
  }
  @Override
  public List<Authorities> findAll() {
    // TODO Auto-generated method stub
    return authoritiesJparepository.findAll();
  }
  
}
