package com.sb.demo.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Value(PropertyNames.CLOUDANT_HOST_URL)
	private String cloudantHost;


	@Value(PropertyNames.CLOUDANT_PASSWORD)
	private String cloudantPassword;


	public String getCloudantHost() {
		return cloudantHost;
	}




	public String getCloudantPassword() {
		return cloudantPassword;
	}





}
