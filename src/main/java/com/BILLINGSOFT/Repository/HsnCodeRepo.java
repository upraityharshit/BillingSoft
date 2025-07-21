package com.BILLINGSOFT.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BILLINGSOFT.Entity.HsnCode;

public interface HsnCodeRepo extends JpaRepository<HsnCode, Long>{

    public HsnCode findByHsncode(String hsncode);

    @Query("SELECT MAX(u.id) FROM HsnCode u")
    public Integer findMaxHsnCodeId();

    @Query("SELECT u FROM HsnCode u order by id")
    public List<HsnCode> findAllOrderByHsnid();
    
}
