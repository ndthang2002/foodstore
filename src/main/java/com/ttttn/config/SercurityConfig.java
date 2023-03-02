//package com.ttttn.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SercurityConfig  extends WebSecurityConfigurerAdapter{
//  
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//      http
//          .authorizeRequests()
//              .antMatchers("/admin/**").hasRole("ADMIN")
//              .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//              .anyRequest().authenticated()
//              .and()
//          .formLogin()
//              .loginPage("/login")
//              .permitAll()
//              .and()
//          .logout()
//              .permitAll();
//  }
//}
