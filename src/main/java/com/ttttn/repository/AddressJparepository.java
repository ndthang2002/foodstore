package com.ttttn.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttttn.entity.Address;
import com.ttttn.entity.Authorities;

@Repository
public interface AddressJparepository extends JpaRepository<Address, Integer>{
  @Query(value="SELECT  * from address where user_id=?1" , nativeQuery =true)
  Address findAdressByUser (Integer id);
}

