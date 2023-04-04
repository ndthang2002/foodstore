package com.ttttn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.ImageProduct;
import com.ttttn.repository.ImageProductJparepository;
import com.ttttn.service.ImageProductService;

@Service
public class ImageProductServiceImpl implements ImageProductService{

  @Autowired
  ImageProductJparepository imageProductJparepository;

  @Override
  public List<ImageProduct> findImageProById(Integer id) {
    // TODO Auto-generated method stub
    return imageProductJparepository.findimageProductById(id);
  }


  


}
