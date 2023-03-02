package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Review;

@Repository
public interface ReviewJparepository extends JpaRepository<Review, Integer>{

}
