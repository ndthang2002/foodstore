package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.User;

@Repository
public interface UserJparepository  extends JpaRepository<User, Integer>{

}
