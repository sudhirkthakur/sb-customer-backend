package com.sb.demo.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
	
	public void setCloudantHost(String cloudantHost) {
		this.cloudantHost = cloudantHost;
	}

	public void setCloudantPassword(String cloudantPassword) {
		this.cloudantPassword = cloudantPassword;
	}

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

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
