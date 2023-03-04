package com.ttttn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
public class Authorities {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer  authoritiesid;
  
  @ManyToOne
  @JoinColumn(name = "userid")
  private User user;
    
  @ManyToOne
  @JoinColumn(name = "roleid")
  private Role role;
}
