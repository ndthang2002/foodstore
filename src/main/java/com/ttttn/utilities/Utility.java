package com.ttttn.utilities;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
@Component
public class Utility {
  public static String getSiteURL(HttpServletRequest request) {
    String siteURL = request.getRequestURI().toString();
    return siteURL.replace(request.getServletPath(), "http://localhost:8080");
  }
  

}
