package com.BILLINGSOFT.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BILLINGSOFT.Entity.Permissions;

public interface  PermissionRepository extends JpaRepository<Permissions, Integer>{

    public List<Permissions> findByUsername(String usrename);

    public Permissions findByUsernameAndMenuGroupAndMenuName(String username, String menuGroup, String menuName);
    
}
