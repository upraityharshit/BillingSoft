package com.BILLINGSOFT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BILLINGSOFT.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public User findUserByUsername(String user);

    @Query("SELECT MAX(u.user_id) FROM User u")
    public int findMaxUserId();
    
}
