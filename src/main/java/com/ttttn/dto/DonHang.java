package com.ttttn.dto;



import java.util.Date;

import lombok.Data;

@Data
public class DonHang {
  
  private int maDonHang;
  private String tenKhachHang;
  private Date ngayDat;
  private String trangThai;
  private double tongTien;
  private int soLuongDH;
  
}
