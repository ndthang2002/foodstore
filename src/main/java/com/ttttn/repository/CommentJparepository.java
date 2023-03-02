package com.ttttn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Comment;

@Repository
public interface CommentJparepository extends JpaRepository<Comment, Integer>{

}
