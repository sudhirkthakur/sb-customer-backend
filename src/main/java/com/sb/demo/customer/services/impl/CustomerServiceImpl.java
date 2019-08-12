package com.sb.demo.customer.services.impl;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.demo.customer.config.ApplicationConfig;
import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;
import com.sb.demo.customer.services.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private ObjectMapper mapper;

	@Autowired
	private ApplicationConfig appConfig;

	@Autowired
	public CustomerServiceImpl(ObjectMapper mapper, ApplicationConfig appConfig) {

		this.mapper = mapper;
		this.appConfig = appConfig;
	}

	public List<CustomerResponseRows> getAllCustomers() {

		final String uri = appConfig.getCloudantHost() + "customers/_all_docs?include_docs=true";
		
		logger.info("Inside the getAllCustomers " );

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<CustomerResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
				CustomerResponse.class);
		

		return result.getBody().getRows();
	}

	public CustomerResponseDocs getCustomer(String id) {

		CustomerResponseDocs customerResponseDocs = null;
		try {

			final String uri = appConfig.getCloudantHost() + "customers/" + id;

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<CustomerResponseDocs> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
					CustomerResponseDocs.class);
			
			if (result != null && result.getStatusCode().equals(HttpStatus.OK) && result.getBody() != null) {
				customerResponseDocs = result.getBody();
			}

		} catch (HttpClientErrorException ex) {
			

		} catch (HttpServerErrorException ex) {
			
		}

		return customerResponseDocs;

	}

	public CustomerCreateResponse createCustomer(CreateCustomer createCustomer) {

		final String uri = appConfig.getCloudantHost() + "customers";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity;
		ResponseEntity<CustomerCreateResponse> result = null;
		try {
			entity = new HttpEntity<String>(mapper.writeValueAsString(createCustomer), headers);
			result = restTemplate.exchange(uri, HttpMethod.POST, entity, CustomerCreateResponse.class);
			

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return result.getBody();
	}

	public CustomerCreateResponse updateCustomer(String id, String rev, CreateCustomer createCustomer) {


		final String uri = appConfig.getCloudantHost() + "customers";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity;
		ResponseEntity<CustomerCreateResponse> result = null;
		try {
			entity = new HttpEntity<String>(mapper.writeValueAsString(createCustomer), headers);
			result = restTemplate.exchange(uri, HttpMethod.POST, entity, CustomerCreateResponse.class);
			

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.getBody();
	}

	public CustomerCreateResponse deleteCustomer(String id, String rev) {

		final String uri = appConfig.getCloudantHost() + "customers/" + id + "?rev=" + rev;

		

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<CustomerCreateResponse> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity,
				CustomerCreateResponse.class);
		

		return result.getBody();
	}
}
