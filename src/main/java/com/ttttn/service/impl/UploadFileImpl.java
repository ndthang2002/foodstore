package com.ttttn.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttttn.service.UploadFileService;

@Service
public class UploadFileImpl implements UploadFileService{
  
  @Autowired
  ServletContext servletContext;

  @Override
  public File save(MultipartFile file ) {
    // TODO Auto-generated method stub
    try {
      // Lấy tên file ảnh
      String fileName = file.getOriginalFilename();
      // Lưu file ảnh vào thư mục
      File dir=new File(servletContext.getRealPath("/static/assets/imgNhanvien"));
      if (!dir.exists()) {
        dir.mkdir();
      }
      String s=System.currentTimeMillis()+file.getOriginalFilename();
      String name=Integer.toHexString(s.hashCode())+s.substring(s.lastIndexOf("."));
      try {
          File savedFile=new File(dir,name);
          file.transferTo(savedFile);
          System.out.println(savedFile.getAbsolutePath());
          return savedFile;
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  
    } catch (Exception e) {
      e.printStackTrace();
      
    }
    return null;
    
    
  }


}
