package com.BILLINGSOFT.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final int MAX_FAILED_ATTEMPTS = 3;

    @Transactional
    public void increaseFailedAttempts(User user) {
    
        int newFailAttempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(newFailAttempts);

        if (newFailAttempts >= MAX_FAILED_ATTEMPTS) {
            user.setIslocked(true);
            user.setFailedAttempts(0);
        }

        userRepository.save(user);
    }
    
    @Transactional
    public void resetFailedAttempts(User user) {
        user.setFailedAttempts(0);
        userRepository.save(user);
    }
}

