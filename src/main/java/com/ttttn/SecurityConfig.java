package com.ttttn;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.ttttn.entity.Account;
import com.ttttn.service.AccountService;
import com.ttttn.utilities.CookieService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  HttpServletRequest    request;
  @Autowired
  AccountService        accountService;
  BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
  CookieService cookie;
  public static String nameAccount;
  public static boolean isLogedIn;
  public static Account accountLogedIn;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    return auth.getAuthenticationManager();
    auth.userDetailsService(Username -> {
      try {
        // Kiểm tra tài khoản trong data
       
        Account user = accountService.findAccountByUserName(Username);
        nameAccount = user.getName();
        accountLogedIn = user;
        // Kiểm tra pass  
        String password = pe.encode(user.getPassword());

        // Kiểm tea quyền
  
        String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getName()).collect(Collectors.toList())
            .toArray(new String[0]);
        
//cookie.add("username", Username, 720);
//      cookie.add("password", password, 720);
      isLogedIn=true;
      
        return User.withUsername(Username).password(password).roles(roles).build();
        //luu tai vaoo cookie 
        
      
      } catch (Exception e) {
        e.printStackTrace();
        throw new UsernameNotFoundException(Username + "not found!");
      }
    });
  }

//  @Bean
//  public InMemoryUserDetailsManager userDetailsService() {
//    UserDetails user = User.withUsername("tan@11")
//        .password(passwordEncoder().encode("123456tan"))
//        .roles("user")
//        .build();
//    UserDetails admin = User.withUsername("admin")
//        .password(passwordEncoder().encode("123456admin"))
//        .roles("admin")
//        .build();
//    return new InMemoryUserDetailsManager(user,admin);
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // tắt CSRF, CORS
    http.cors().disable().authorizeRequests().antMatchers("/index", "/categoryid", "/product",
        "/detail", "/search", "/assets/css/**", "/assets/js/**", "/assets/images/**", "/assets/fonts/**","/categoryid**").permitAll()
        .antMatchers("/cart","/payments").hasRole("user").and();

    // dang nhap
    http.formLogin().loginPage("/login").loginProcessingUrl("/security/login").defaultSuccessUrl("/index")
        .failureUrl("/failureLogin");
    http.rememberMe().tokenValiditySeconds(86400);
    http.exceptionHandling().accessDeniedPage("/security/aunauthoried");
    
    // dang xuat
    http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/logout");
    //dang nhap bang fa gg
    http.oauth2Login().loginPage("/login")
    .defaultSuccessUrl("/index",true)
    .failureUrl("/login")
    .authorizationEndpoint()
    .baseUri("/security2/authrization");
  }

  
  
  public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) { 
    String fullname = oauth2.getPrincipal().getAttribute("name");
    String name = oauth2.getName();
    System.out.println(name);
    nameAccount =fullname;
    //String email = oauth2.getPrincipal().getAttribute("email");
    String password = Long.toHexString(System.currentTimeMillis());
    UserDetails user = User.withUsername(name).password(pe.encode(password)).roles("GUEST").build();
    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(auth);
  }
  
  
//Cho phép truy xuất REST API từ domain khác
 @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
   }
}
