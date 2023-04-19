package com.ttttn.service;

import com.ttttn.entity.Address;

public interface AddressService {
  Address insert(Address address);
  
  Address findAddressByUser(Integer id);
}
