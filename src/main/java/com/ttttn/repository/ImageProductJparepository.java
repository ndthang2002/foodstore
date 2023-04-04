package com.ttttn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttttn.entity.ImageProduct;
import com.ttttn.entity.Product;


@Repository
public interface ImageProductJparepository extends JpaRepository<ImageProduct, Integer>{
  @Query(value="SELECT * FROM image_product WHERE  product_id=?1",nativeQuery = true)
  List<ImageProduct> findimageProductById(Integer cid);
}
