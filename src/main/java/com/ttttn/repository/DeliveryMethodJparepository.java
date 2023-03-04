package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.DeliveryMothod;

@Repository
public interface DeliveryMethodJparepository extends JpaRepository<DeliveryMothod,Integer>{

}
