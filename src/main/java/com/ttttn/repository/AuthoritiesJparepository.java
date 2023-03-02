package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Authorities;

@Repository
public interface AuthoritiesJparepository extends JpaRepository<Authorities, Integer>{

}
