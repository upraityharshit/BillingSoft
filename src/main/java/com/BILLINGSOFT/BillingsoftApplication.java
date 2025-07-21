package com.BILLINGSOFT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BillingsoftApplication extends SpringBootServletInitializer{ 
	public static void main(String[] args) {
		SpringApplication.run(BillingsoftApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BillingsoftApplication.class);
	}
	
}
