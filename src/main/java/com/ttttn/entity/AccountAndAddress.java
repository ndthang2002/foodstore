package com.ttttn.entity;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class AccountAndAddress {
  private Account account;
  private Address address;
  private Role role;
  public AccountAndAddress(Account account, Address address,Role role) {
      this.account = account;
      this.address = address;
      this.role = role;
      
  }

  public Account getAccount() {
      return account;
  }

  public void setAccount(Account account) {
      this.account = account;
  }

  public Address getAddress() {
      return address;
  }

  public void setAddress(Address address) {
      this.address = address;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  
}
