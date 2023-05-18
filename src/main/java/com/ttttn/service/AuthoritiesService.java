package com.ttttn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttttn.entity.Authorities;


public interface AuthoritiesService {
  Authorities insert(Authorities authorities) ;
  Integer findIdRoleByUser(Integer id);
  Authorities findAuthoritiesByUser(Integer idUser);
  void delete(Authorities authorities);
  List<Authorities> findAll();
}
