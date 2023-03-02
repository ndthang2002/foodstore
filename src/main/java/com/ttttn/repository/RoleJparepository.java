package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Role;

@Repository
public interface RoleJparepository extends JpaRepository<Role, Integer>{

}
