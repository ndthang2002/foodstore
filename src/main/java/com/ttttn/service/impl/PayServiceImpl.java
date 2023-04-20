package com.ttttn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Payment;
import com.ttttn.repository.PayJparepository;
import com.ttttn.service.PayService;

@Service
public class PayServiceImpl implements PayService{

  @Autowired
  PayJparepository payJparepository;
  @Override
  public Payment insert(Payment payment) {
    // TODO Auto-generated method stub
    return payJparepository.save(payment);
  }

}
