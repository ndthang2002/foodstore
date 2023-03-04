package com.ttttn.service;

import java.util.List;


import com.ttttn.entity.Category;
import com.ttttn.entity.Comment;
import com.ttttn.entity.Product;
import com.ttttn.entity.User;

public interface CommentService {
  List<Comment> finAll();

  Comment finById(Integer id);

  Comment add(Comment comment);
  
  List<Product> findByProductId(String pid);
  
  List<User> findByUserId(String uid);
  
  void deleteid(Integer id);

  Comment update(Integer id);

}
