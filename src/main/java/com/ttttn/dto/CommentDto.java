package com.ttttn.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommentDto {
  private Integer idcomment;
  private Integer idaccount;
 private String image;
 private String nameaccount;
 private Long date;
 private Integer star;  
 private String content;
  
}
