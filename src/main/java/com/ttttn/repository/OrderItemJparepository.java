package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Cart;
import com.ttttn.entity.OrderItems;

@Repository
public interface OrderItemJparepository extends JpaRepository<OrderItems, Integer>{

}
