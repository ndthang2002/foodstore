package com.ttttn.restcontroller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ttttn.SecurityConfig;
import com.ttttn.controller.ProductController;
import com.ttttn.dto.CommentDto;
import com.ttttn.entity.Account;
import com.ttttn.entity.Category;
import com.ttttn.entity.Comment;
import com.ttttn.entity.ImageProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.AccountService;
import com.ttttn.service.CategoryService;
import com.ttttn.service.CommentService;
import com.ttttn.service.ImageProductService;
import com.ttttn.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest/")
public class ProductRestController {

  @Autowired
  AccountService accountService;
  @Autowired
  CategoryService categoryService;
  @Autowired
  ProductService productService;
  @Autowired
  ImageProductService imageProductService;
  SecurityConfig config;
  ProductController pController;
  
  @Autowired
  CommentService commentService;
  @GetMapping("getcategory")
  public List<Category> getCategory(){
    List<Category> list  = categoryService.findAll();
    return list;
  }
  
  @GetMapping("getAllProduct")
  public List<Product> getListProduct(){
    List<Product> list = productService.fillAll();
    return list;
  }
  
  @DeleteMapping("delete/{id}")
  public void delete(@PathVariable("id") Integer id) {
    Product product = productService.findById(id);
    List<ImageProduct> imageProduct = imageProductService.findImageProById(id);
    for(ImageProduct list :imageProduct) {
      imageProductService.delete(list);
    }
    productService.delete(product);
  }
  @GetMapping("getCateByIdPro/{id}")
  public Category get(@PathVariable("id") Integer idp) {
    Category category = categoryService.findByid(productService.findIdCaByIdP(idp));
    return category;
  }
  
  @PostMapping("updateProduct/{idp}")
  @ResponseBody
  public Product updateProduct(@PathVariable("idp") Integer idp,
      @RequestParam("file1") MultipartFile file1,
      @RequestParam("file2") MultipartFile file2,
      @RequestParam("file3") MultipartFile file3,
      @RequestParam("file4") MultipartFile file4,
      @RequestParam("name") String name,
      @RequestParam("model") String model,
      @RequestParam("quantity") Integer quantity,
      @RequestParam("price") Integer price,
      @RequestParam("categoryid") Integer categoryid
    )
  {
  
    Category category = categoryService.findByid(categoryid);
    Product pro = productService.findById(idp);
    pro.setCategory(category);
    pro.setModel(model);
    pro.setQuantity(quantity);
    pro.setPrice(price);
    pro.setName(name);
    pro.setOrderItems(null);
    // up anh chinh up anh phu len 
    //up anh len clound va vao database
    Map<String, String> config = new HashMap<>();
    config.put("cloud_name", "dpbixmrep");
    config.put("api_key", "132427124622788");
    config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");

    Cloudinary cloudinary = new Cloudinary(config);
   
    Map params = ObjectUtils.asMap(
         // Nếu trùng tên cũ sẽ ghi đè 
        
        "upload_preset", "v9e7akio"
    );
    if (!file1.isEmpty()) {
      try {
        Object res = cloudinary.uploader().upload(file1.getBytes(), params);
        // URL để lưu vào database
        String urlUploaded = ((Map<String, String>) res).get("url");
        String baseUrl = "http://res.cloudinary.com/dpbixmrep/image/upload/";
        String path = urlUploaded.replace(baseUrl, "");
        pro.setImage(path);
        
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }else {
      System.out.println("anh 1 null");
    }
    productService.insert(pro);
    // update anh phu
    List<MultipartFile> ImageProducts = new ArrayList<>();
    ImageProducts.add(file2);
    ImageProducts.add(file3);
    ImageProducts.add(file4);
    for(MultipartFile listI : ImageProducts) {
      if (!listI.isEmpty()) {
        
        ImageProduct imageProduct = new ImageProduct();
        imageProduct.setImagedescription("anh chi tiet "+listI.getName());
        imageProduct.setProduct(pro);
        //up anh len clound va vao database
        Map<String, String> configg = new HashMap<>();
        config.put("cloud_name", "dpbixmrep");
        config.put("api_key", "132427124622788");
        config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");
        Cloudinary cloudinaryy = new Cloudinary(configg);
       
        Map paramss = ObjectUtils.asMap(
             // Nếu trùng tên cũ sẽ ghi đè 
            "upload_preset", "v9e7akio"
        );
          try {
            Object res = cloudinary.uploader().upload(listI.getBytes(), params);
            // URL để lưu vào database
            String urlUploaded = ((Map<String, String>) res).get("url");
            String baseUrl = "http://res.cloudinary.com/dpbixmrep/image/upload/";
            String path = urlUploaded.replace(baseUrl, "");
            imageProduct.setUrlname(path);
            imageProductService.insert(imageProduct);
          } catch (IOException exception) {
            exception.printStackTrace();
          }
      }else {
        System.out.println("anh null");
      }
    }
    return pro;
    
  }
  @PostMapping("/test")
  public String get(@RequestParam("file") MultipartFile file) {
    
    System.out.println(file.getOriginalFilename());
    return "thancong";
  }
  
  // comment san pham 
  // them moi comment
  @PostMapping("uploadcomment")
  public CommentDto saveComment(@RequestBody String textComment) {
    Product product = productService.findById(pController.IdProduct);
    Account account = config.accountLogedIn;
    Comment comment = new Comment();
    String cleanText = textComment.replaceAll("[^\\p{L}\\p{N}\\s]+", "");
    comment.setContent(cleanText);
    comment.setDatesubmited(new Date());
    comment.setStar(0);
    comment.setUser(account);
    comment.setProduct(product);
    comment= commentService.insert(comment);
    
    // tao dto hien thi
    //hien thị thời gian kể từ lúc comment đến bây giờ'
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = comment.getDatesubmited().toInstant().atZone(zoneId).toLocalDateTime();

    LocalDateTime now = LocalDateTime.now();
    Duration duration = Duration.between(localDateTime, now);
    long minutes = duration.toMinutes();
    long hours = duration.toHours();
    long days = duration.toDays();
    
    CommentDto  commentDto = new CommentDto();
    commentDto.setContent(textComment);
    commentDto.setDate(days);
    commentDto.setImage(comment.getUser().getImage());
    commentDto.setNameaccount(comment.getUser().getName());
    commentDto.setStar(comment.getStar());
    return commentDto;
    
  }
  // la ra toan bo comment theo tung san pham
  @GetMapping("getallcomment")
  public List<CommentDto> getAllComment(){
    List<Comment> listcomComments = commentService.getListcomentbyPro(pController.IdProduct);
    List<CommentDto> commentDtos = new ArrayList<>() ;
    
    for(Comment comment :listcomComments) {
      CommentDto commentDto = new CommentDto();
      // lay ra anh va ten nguoi dung
      Account account = comment.getUser();
    //hien thị thời gian kể từ lúc comment đến bây giờ'
      ZoneId zoneId = ZoneId.systemDefault();
      LocalDateTime localDateTime = comment.getDatesubmited().toInstant().atZone(zoneId).toLocalDateTime();

      LocalDateTime now = LocalDateTime.now();
      Duration duration = Duration.between(localDateTime, now);
      long minutes = duration.toMinutes();
      long hours = duration.toHours();
      long days = duration.toDays();
      commentDto.setDate(days);
      commentDto.setContent(comment.getContent());
      commentDto.setImage(account.getImage());
      commentDto.setNameaccount(account.getName());
      commentDto.setStar(comment.getStar());
      commentDto.setIdaccount(account.getUserid());
      commentDto.setIdcomment(comment.getCommentid());
      commentDtos.add(commentDto);
    }
    return commentDtos;
    
  }
  
  @DeleteMapping("deletecomment/{id}")
  public void deleteComment(@PathVariable("id") Integer id) {
    commentService.deleteid(id);
  
  }
}
