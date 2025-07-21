package com.BILLINGSOFT.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class MyConfig{
	
	 @Autowired
	 private AuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@SuppressWarnings("removal")
	@Bean
	protected AuthenticationManager authenticationManager(HttpSecurity http) 
	  throws Exception {
		
		  return http.getSharedObject(AuthenticationManagerBuilder.class)
		  .userDetailsService(this.getUserDetailsService())
		  .passwordEncoder(this.passwordEncoder()) .and() .build();	   
	}
	
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(requests -> requests
				.requestMatchers("/dashboard/**").authenticated() // Require authentication for all other requests
				.requestMatchers("/**").permitAll()
	        )
	        .formLogin(login -> login
	            .loginPage("/login") // Custom login page
	            .failureHandler(customAuthenticationFailureHandler)
	            .loginProcessingUrl("/do_login") // URL to submit the username and password
	            .defaultSuccessUrl("/dashboard/home", true) // Redirect to the dashboard after successful login
	        );

	    return http.build();
	}
}
