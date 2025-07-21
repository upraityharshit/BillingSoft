package com.BILLINGSOFT.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BILLINGSOFT.Entity.Finyear;

public interface FinyearRepo extends JpaRepository<Finyear, Long>{

    public Finyear findByName(String name);

    @Query("SELECT MAX(u.id) FROM Finyear u")
    public Integer findMaxFinId();

    @Query("SELECT u FROM Finyear u ORDER BY name DESC")
    public List<Finyear> findAllOrderByFinid();

    @Query("SELECT u FROM Finyear u WHERE u.active=true ORDER BY name DESC")
    public List<Finyear> findAllActiveYear();
    
    
}
