package com.ttttn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ttttn.SecurityConfig;



@Component
public class Globallnterceptor implements HandlerInterceptor {
 SecurityConfig acc;
	// khai báo toàn chương trình hiển thị loại sản phẩm và thương hiệu
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	 request.setAttribute("account", acc.nameAccount);
	}

	
}
