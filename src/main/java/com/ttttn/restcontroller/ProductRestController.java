package com.ttttn.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import com.ttttn.entity.Category;
import com.ttttn.entity.ImageProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.CategoryService;
import com.ttttn.service.ImageProductService;
import com.ttttn.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest/")
public class ProductRestController {

  @Autowired
  CategoryService categoryService;
  @Autowired
  ProductService productService;
  @Autowired
  ImageProductService imageProductService;
  
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
  
}
