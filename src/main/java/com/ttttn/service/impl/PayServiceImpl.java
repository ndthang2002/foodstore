package com.ttttn.service.impl;

import java.util.List;

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
  @Override
  public List<Payment> findPaymentsbbyOrder(Integer orderid) {
    // TODO Auto-generated method stub
    return payJparepository.findPayByOrder(orderid);
  }
  @Override
  public void delete(Payment payment) {
    // TODO Auto-generated method stub
    payJparepository.delete(payment);
  }

}
