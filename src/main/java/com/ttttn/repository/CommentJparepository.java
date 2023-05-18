package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.CartProduct;
import com.ttttn.entity.Comment;

@Repository
public interface CommentJparepository extends JpaRepository<Comment, Integer>{

  
  @Query(value="select * FROM  comment  where product_id = ?1 " , nativeQuery =true)
  List<Comment> getListcomentbyPro (Integer pid);

}
