package com.ttttn.service;

import org.springframework.stereotype.Service;

import com.ttttn.entity.Authorities;


public interface AuthoritiesService {
  Authorities insert(Authorities authorities) ;
  Integer findIdRoleByUser(Integer id);
}
