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
    Address address =null;
    try {
      
      address = addressJparepository.findAdressByUser(id);
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println("không tìm thấy addres theo nguoi dung nay ");
    }
    return address;
  }



  @Override
  public void delete(Address address) {
    // TODO Auto-generated method stub
    addressJparepository.delete(address);
  }

}
