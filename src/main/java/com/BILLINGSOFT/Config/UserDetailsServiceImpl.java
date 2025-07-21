package com.BILLINGSOFT.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching user from database
		User user = this.userRepository.findUserByUsername(username);
		
		if (user.isIslocked()) {
            throw new LockedException("User account is locked, Please contact to administrator");
        }
		
		CustomerUserDetails customerUserDetails =new CustomerUserDetails(user);
		
		return customerUserDetails;
	}

}
