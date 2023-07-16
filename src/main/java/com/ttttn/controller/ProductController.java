package com.ttttn.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ttttn.entity.Category;
import com.ttttn.entity.ImageProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.CategoryService;
import com.ttttn.service.ImageProductService;
import com.ttttn.service.ProductService;

@Controller
public class ProductController {
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final int LENGTH = 10;

  @Autowired
  ProductService      productService;

  @Autowired
  CategoryService     categoryService;

  @Autowired
  ImageProductService imageProductService;
  public static Integer IdProduct;


  @RequestMapping("/product")
//  public String product(Model model) 
//      List<Product> listProduct = productService.fillAll();
//      model.addAttribute("listProduct", listProduct);
//     
//  List<Category> listCategory = categoryService.findAll();
//  model.addAttribute("listCategory", listCategory);
//    return "product/products";
  
  public String listBooks(Model model, @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {
    List<Category> listCategory = categoryService.findAll();
    model.addAttribute("listCategory", listCategory);
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(8);

    Page<Product> coursePage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

    model.addAttribute("listProduct", coursePage);

    int totalPages = coursePage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "product/products";
  }

  @GetMapping("/categoryid")
  public String showProductByCategoryid(Model model, @RequestParam("cid") String id) {
    try {
      System.out.println("vossss");
      Page<Product> listProductbyCategory = productService.findByCategoryId(id);

      model.addAttribute("listProduct", listProductbyCategory);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return "product/products";
  }

  @RequestMapping("/detail")
  public String showProductDetail(Model model, @RequestParam("pid") Integer id) {
    this.IdProduct = id;
    Product product = new Product();
    product = productService.findById(id);
    model.addAttribute("product", product);
    List<ImageProduct> listImageProduct = imageProductService.findImageProById(id);
    model.addAttribute("listImageProduct", listImageProduct);
    return "product/product-details";
  }

//  tim kiem san pham 
  @RequestMapping("/search")
  public String timkiem(Model model, @RequestParam("timkiem") String name) {
    Page<Product> list = productService.findProductByName("%" + name + "%");

    model.addAttribute("listProduct", list);
    return "product/products";
  }

  @PostMapping("product/save")
  public String save(

      @RequestParam("category") Integer categoryid,
      @RequestParam("imagePr") MultipartFile imageChinh,
      @RequestParam("name") String name,
      @RequestParam("model") String model,
      @RequestParam("quantity") Integer quantity,
      @RequestParam("price") Integer price,
      @RequestParam("imageF1") MultipartFile imageF1,
      @RequestParam("imageF2") MultipartFile imageF2,
      @RequestParam("imageF3") MultipartFile imageF3
      
  ) {

    Product product = new Product();
    Category category = categoryService.findByid(categoryid);
    product.setCategory(category);
    product.setModel(model);
    product.setName(name.toUpperCase());
    product.setQuantity(quantity);
    product.setPrice(price);
    
    
    
    //up anh len clound va vao database
    Map<String, String> config = new HashMap<>();
    config.put("cloud_name", "dpbixmrep");
    config.put("api_key", "132427124622788");
    config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");

    Cloudinary cloudinary = new Cloudinary(config);
    String nameImage=generateRandomText();
    Map params = ObjectUtils.asMap(
         // Nếu trùng tên cũ sẽ ghi đè 
      
        "upload_preset", "v9e7akio"
    );
    if (!imageChinh.isEmpty()) {
      try {
        Object res = cloudinary.uploader().upload(imageChinh.getBytes(), params);
        // URL để lưu vào database
        String urlUploaded = ((Map<String, String>) res).get("url");
        String baseUrl = "http://res.cloudinary.com/dpbixmrep/image/upload/";
        String path = urlUploaded.replace(baseUrl, "");
        product.setImage(path);
        
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }
    productService.insert(product);
    
    //them anh phu vao nếu người dùng có tải lên 
    List<MultipartFile> ImageProducts = new ArrayList<>();
    ImageProducts.add(imageF1);
    ImageProducts.add(imageF2);
    ImageProducts.add(imageF3);
    for(MultipartFile listI : ImageProducts) {
      if (!listI.isEmpty()) {
        
        ImageProduct imageProduct = new ImageProduct();
        imageProduct.setImagedescription("anh chi tiet "+listI.getName());
        imageProduct.setProduct(product);
        //up anh len clound va vao database
        Map<String, String> configg = new HashMap<>();
        config.put("cloud_name", "dpbixmrep");
        config.put("api_key", "132427124622788");
        config.put("api_secret", "KI7WCnrGYQ1Hxkqj-mmHBT-6EBg");
        Cloudinary cloudinaryy = new Cloudinary(configg);
        String nameImagee=generateRandomText();
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
    
    return "redirect:/admin/sanpham";
  }
  // ram dom ten cua image
  public static String generateRandomText() {
    Random random = new Random();
    StringBuilder sb = new StringBuilder(LENGTH);
    for (int i = 0; i < LENGTH; i++) {
        int index = random.nextInt(CHARACTERS.length());
        sb.append(CHARACTERS.charAt(index));
    }
    return sb.toString();
}
  


}
