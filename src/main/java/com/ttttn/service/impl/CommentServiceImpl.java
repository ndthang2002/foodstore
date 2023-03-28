package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Category;
import com.ttttn.entity.Comment;
import com.ttttn.entity.Product;
import com.ttttn.entity.Account;
import com.ttttn.repository.CommentJparepository;
import com.ttttn.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentJparepository commentJparepository;

  @Override
  public List<Comment> finAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment finById(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment add(Comment comment) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Product> findByProductId(String pid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Account> findByUserId(String uid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteid(Integer id) {
    // TODO Auto-generated method stub
  }

  @Override
  public Comment update(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
