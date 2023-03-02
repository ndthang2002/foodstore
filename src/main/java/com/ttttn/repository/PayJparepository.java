package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Pay;

@Repository
public interface PayJparepository extends JpaRepository<Pay, Integer>{

}
