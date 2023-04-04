package com.ttttn.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttttn.entity.Category;
import com.ttttn.entity.ImageProduct;
import com.ttttn.entity.Product;
import com.ttttn.service.CategoryService;
import com.ttttn.service.ImageProductService;
import com.ttttn.service.ProductService;

@Controller
public class ProductController {
  
  @Autowired
  ProductService productService;
  
  @Autowired
  CategoryService categoryService;
  
  @Autowired
  ImageProductService imageProductService;
  
  
  @RequestMapping("/product")
//  public String product(Model model) 
    
//      List<Product> listProduct = productService.fillAll();
//      model.addAttribute("listProduct", listProduct);
//     
//  List<Category> listCategory = categoryService.findAll();
//  model.addAttribute("listCategory", listCategory);
//    return "product/products";
    public String listBooks(
        Model model,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size) {
  List<Category> listCategory = categoryService.findAll();
  model.addAttribute("listCategory", listCategory);
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(8);

    Page<Product> coursePage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

    model.addAttribute("listProduct", coursePage);

    int totalPages = coursePage.getTotalPages();
    if (totalPages > 0) {
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
    }

 return "product/products";
  }
    
  
  

  @GetMapping("/categoryid")
  public String showProductByCategoryid(Model model , @RequestParam("cid") String id) {
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
  public String showProductDetail(Model model ,@RequestParam("pid") Integer id) {
    Product product = new Product();
    product = productService.findById(id);
    model.addAttribute("product", product);
    List<ImageProduct> listImageProduct =  imageProductService.findImageProById(id);
    model.addAttribute("listImageProduct", listImageProduct);
    return "product/product-details";
  }
  
//  tim kiem san pham 
  @RequestMapping("/search")
  public String timkiem(Model model,@RequestParam("timkiem") String name){
     Page<Product> list = productService.findProductByName("%"+name+"%");
    
    model.addAttribute("listProduct", list);
    return "product/products";
  }
}
