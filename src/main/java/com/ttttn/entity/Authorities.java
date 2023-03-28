package com.ttttn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userid", "roleid"})
})
public class Authorities {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer  authoritiesid;
  
  @ManyToOne
  @JoinColumn(name = "userid")
  private Account user;
    
  @ManyToOne
  @JoinColumn(name = "roleid")
  private Role role;
  
}
