package com.sb.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sb.demo.customer.config.ApplicationConfig;

@SpringBootApplication
@ComponentScan(basePackageClasses = {  CustomerApplication.class })
public class CustomerApplication {

	
	@Autowired
	private ApplicationConfig applicationConfig;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
