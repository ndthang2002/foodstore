package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Authorities;
import com.ttttn.entity.Cart;

@Repository
public interface AuthoritiesJparepository extends JpaRepository<Authorities, Integer>{

  @Query(value="SELECT  roleid from authorities where userid=?1" , nativeQuery =true)
  Integer findIdRoleByUser (Integer id);
 
  @Query(value=" select * from authorities  where userid =?1" , nativeQuery =true)
  Authorities findAuthoritiesByUser (Integer id);
}
  