package com.ttttn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttttn.entity.ImageProduct;


public interface ImageProductService {
  
  List<ImageProduct> findImageProById(Integer id);
  ImageProduct insert(ImageProduct imageProduct);
  void  delete(ImageProduct imageProduct);
}
