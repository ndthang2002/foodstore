package com.ttttn.restcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ttttn.SecurityConfig;
import com.ttttn.entity.Account;
import com.ttttn.entity.AccountAndAddress;
import com.ttttn.entity.Address;
import com.ttttn.entity.Authorities;
import com.ttttn.entity.Role;
import com.ttttn.service.AccountService;
import com.ttttn.service.AddressService;
import com.ttttn.service.AuthoritiesService;
import com.ttttn.service.DeliveryMethodService;
import com.ttttn.service.OrderItemService;
import com.ttttn.service.OrderService;
import com.ttttn.service.PayService;
import com.ttttn.service.RoleService;
import com.ttttn.service.UploadFileService;

@CrossOrigin("*")
@RestController

public class AccountRestController {

  @Autowired
  AccountService        accountService;

  @Autowired
  AddressService        addressService;

  @Autowired
  AuthoritiesService    authoritiesService;

  @Autowired
  UploadFileService     uploadFileService;

  @Autowired
  ServletContext        app;

  @Autowired
  RoleService           roleService;

  @Autowired
  OrderService          orderService;
  @Autowired
  DeliveryMethodService deliveryMethodService;
  @Autowired
  OrderItemService      orderItemService;
  @Autowired
  PayService            payService;

  SecurityConfig        config;

  public static String  images;

  @GetMapping("/rest/account/loged/")
  public Account getAcc() {
    Account account = new Account();
    account = accountService.findbyid(config.accountLogedIn.getUserid());
    return account;
  }

  // lay ra toan bo vai tro
  @GetMapping("rest/allrole")
  public List<Role> getRoles() {
    return roleService.getAllRole();
  }

  // add nhanvien da được thay thế bằng java controller
  /*
   * @PostMapping("/rest/nhanvien/create/{id}") public Account create(@RequestBody
   * AccountAndAddress request, @PathVariable("id") Integer roleid) {
   * 
   * Account account = request.getAccount(); Address address =
   * request.getAddress();
   * 
   * MultipartFile file = request.getFile();
   * System.out.println(file.getOriginalFilename()); System.out.println("nghad");
   * 
   * System.out.println("b3"+AccountRestController.images); account.setImage(
   * AccountRestController.images); Account acc =accountService.insert(account);
   * Address addres = address; addres.setUser(account);
   * addressService.insert(addres); //insert role Authorities authorities = new
   * Authorities();
   * 
   * Role role = roleService.findById(roleid); authorities.setRole(role);
   * authorities.setUser(account); authoritiesService.insert(authorities); return
   * acc;
   * 
   * }
   */

  @GetMapping("/rest/nhanvien/getlist")
  public List<Account> getList() {
    List<Account> list = accountService.findAll();
    return list;
  }

  // lay dia chi
  @GetMapping("/rest/nhanvien/getAddress/{id}")
  public Address getListAddress(@PathVariable("id") Integer id) {
    Address address = addressService.findAddressByUser(id);
    return address;
  }

  // lay quyen
  @GetMapping("/rest/nhanvien/getAuthorities/{id}")
  public Authorities getAtuAuthorities(@PathVariable("id") Integer id) {
    Authorities authorities = authoritiesService.findAuthoritiesByUser(id);
    return authorities;
  }

  @DeleteMapping("/rest/nhanvien/deleteAccount/{id}")
  public Account deleteAddress(@PathVariable("id") Integer id) {
    Account account = null;
    Address address = addressService.findAddressByUser(id);
    try {

      if (address != null) {
        // xoa dia chi
        System.out.println(address.getAddressid());
        addressService.delete(address);
      }
      // xoa phan quyen
      Authorities authorities = authoritiesService.findAuthoritiesByUser(id);
      if (authorities != null) {
        authoritiesService.delete(authorities);
      }
      account = accountService.findbyid(id);
      accountService.delete(id);
    } catch (Exception e) {

      // TODO: handle exception
    }

    return account;
  }

  // upload
  @PostMapping("/rest/nhanvien/update/{idUser}")
  @ResponseBody
  public Account updateAccount(@PathVariable("idUser") Integer idUser, @RequestParam("image") MultipartFile image,
      @RequestParam("username") String username, @RequestParam("password") String password,
      @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone,
      @RequestParam("roleid") Integer roleid, @RequestParam("cityid") Integer cityid,
      @RequestParam("districtid") Integer districtid, @RequestParam("wardid") Integer wardid) {


    /*
     * Role role = request.getRole(); account.setUserid(idUser);
     * //account.setImage(image.getOriginalFilename());// account =
     * accountService.insert(account);
     */
    Address adres = new Address();
    Account acc = accountService.findbyid(idUser);
    acc.setUsername(username);
    acc.setPassword(password);
    acc.setName(name);
    acc.setEmail(email);
    acc.setPhone(phone);
    // cap nhat anh
    // up anh len clound va vao database
    Map<String, String> config = new HashMap<>();
    config.put("cloud_name", "dpbixmrep");
    config.put("api_key", "132427124622788");
    config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");

    Cloudinary cloudinary = new Cloudinary(config);

    Map params = ObjectUtils.asMap("upload_preset", "v9e7akio");
    if (!image.isEmpty()) {
      try {
        Object res = cloudinary.uploader().upload(image.getBytes(), params);
        // URL để lưu vào database
        String urlUploaded = ((Map<String, String>) res).get("url");
        String baseUrl = "http://res.cloudinary.com/dpbixmrep/image/upload/";
        String path = urlUploaded.replace(baseUrl, "");
        acc.setImage(path);

      } catch (IOException exception) {
        exception.printStackTrace();
      }
    } else {
      System.out.println("anh 1 null");
    }
    // cap nhat dia chi
    // kiem tra dia chi co chua
    if (addressService.findAddressByUser(acc.getUserid()) == null) {
      adres.setCityid(cityid);
      adres.setDistrictid(districtid);
      adres.setWardid(wardid);
      adres.setUser(acc);
      addressService.insert(adres);
    } else {
      /*
       * update neu tai khoan da co
       */
      adres.setAddressid(addressService.findAddressByUser(idUser).getAddressid());
      adres.setCityid(cityid);
      adres.setDistrictid(districtid);
      adres.setWardid(wardid);
      adres.setUser(acc);
      addressService.insert(adres);
    }

    //cap nhat quyen 
    Authorities authorities = authoritiesService.findAuthoritiesByUser(idUser);
    Role role2 = roleService.findById(roleid);
    authorities.setRole(role2);
    authoritiesService.insert(authorities);
    acc = accountService.insert(acc);
    return acc;

  }

  @GetMapping("/rest/authoties/getall")
  public List<Authorities> getAllAuthorities() {
    List<Authorities> list = authoritiesService.findAll();
    return list;
  }
  
  @GetMapping("/rest/getaccounted")
  public Boolean getaccounted() {
    boolean checkLoged = false;
   if(config.accountLogedIn!=null) {
     checkLoged = true;
   }
   return checkLoged;
  }

}
