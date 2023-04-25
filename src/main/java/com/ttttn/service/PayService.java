package com.ttttn.service;

import java.util.List;

import com.ttttn.entity.Payment;

public interface PayService {
  Payment insert(Payment payment);
  List<Payment> findPaymentsbbyOrder(Integer orderid);
  void delete(Payment payment);
}
