package com.ttttn.entity;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAndAddress {
  private Account account;
  private Address address;
  private Role role;


  
}
