package com.ttttn.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
  
  File save(MultipartFile file);
}
