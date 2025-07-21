package com.BILLINGSOFT.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BILLINGSOFT.Entity.MenuLists;

public interface MenuRepository extends JpaRepository<MenuLists, Integer>{

    @Query("SELECT DISTINCT u.menuGroup FROM MenuLists u ORDER BY u.menuGroup")
    List<String> getAllMenuLists();

    @Query("SELECT u.menuName FROM MenuLists u where u.menuGroup= :menuGroup")
    List<String> getMenusByMenugroup(@Param("menuGroup") String menuGroup);

}
