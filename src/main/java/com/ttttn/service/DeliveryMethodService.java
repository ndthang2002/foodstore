package com.ttttn.service;

import com.ttttn.entity.DeliveryMethod;

public interface DeliveryMethodService {
  DeliveryMethod insert(DeliveryMethod deliveryMethod); 
  DeliveryMethod finDeliveryMethod (Integer orderid);
  void delete(DeliveryMethod deliveryMethod);
}
