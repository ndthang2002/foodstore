package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Role;

public interface RoleService {

   Role findbyname (String role);
   Role findById (Integer id);
   List<Role> getAllRole();
}
