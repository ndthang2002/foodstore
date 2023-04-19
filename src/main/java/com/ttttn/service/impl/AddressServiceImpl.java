package com.ttttn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttttn.entity.Address;
import com.ttttn.repository.AddressJparepository;
import com.ttttn.service.AddressService;

@Service
public class AddressServiceImpl  implements AddressService{
  @Autowired
  AddressJparepository addressJparepository;

  @Override
  public Address insert(Address address) {
    // TODO Auto-generated method stub
    return addressJparepository.save(address);
  }

  @Override
  public Address findAddressByUser(Integer id) {
    // TODO Auto-generated method stub
    return addressJparepository.findAdressByUser(id);
  }

}
