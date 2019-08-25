package com.sb.demo.customer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.customer.exceptions.AppException;

@RestController  
public class HealthController {
	
	
	/**
	 * Application health endpoint
	 *	
	 * @param 
	 * @return
	 */

	@RequestMapping(value = "/health", method = RequestMethod.GET)
    public String checkHealth(){
        return "up";
	}


}
