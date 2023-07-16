package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Product;
import com.ttttn.entity.Account;

@Repository
public interface AccountJparepository  extends JpaRepository<Account, Integer>{

  @Query(value="SELECT * FROM user WHERE username=?1" , nativeQuery =true)
  Account findAccountByUserName(String cid);
  
  @Query(value="select * from user where email =?1",nativeQuery =true)
  public Account findByEmail(String email);
  
  public Account findByResetPasswordToken(String token);
}
