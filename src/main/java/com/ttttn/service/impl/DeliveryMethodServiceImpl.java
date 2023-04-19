package com.ttttn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.DeliveryMethod;
import com.ttttn.repository.DeliveryMethodJparepository;
import com.ttttn.service.DeliveryMethodService;

@Service
public class DeliveryMethodServiceImpl implements DeliveryMethodService{

  @Autowired
  DeliveryMethodJparepository deliveryMethodJparepository;
  
  @Override
  public DeliveryMethod insert(DeliveryMethod deliveryMethod) {
    // TODO Auto-generated method stub
    return  deliveryMethodJparepository.save(deliveryMethod);
  }

}
