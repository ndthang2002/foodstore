package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Product;
import com.ttttn.entity.Role;

@Repository
public interface RoleJparepository extends JpaRepository<Role, Integer>{

  @Query(value="SELECT * FROM role WHERE  name like  ?1",nativeQuery = true)
  Role findByKeyword(String keyword);
}
