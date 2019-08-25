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
import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.request.UpdateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;
import com.sb.demo.customer.services.CustomerService;
import com.sb.demo.customer.utils.ErrorUtil;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private ObjectMapper mapper;

	@Autowired
	private RestTemplate template;

	@Autowired
	private ApplicationConfig appConfig;

	String dbName = "customers";

	@Autowired
	public CustomerServiceImpl(RestTemplate template, ObjectMapper mapper, ApplicationConfig appConfig) {
		this.template = template;
		this.mapper = mapper;
		this.appConfig = appConfig;
	}

	/**
	 * Method list the entire customer records from the database.
	 * @throws AppException
	 */

	public List<CustomerResponseRows> getAllCustomers() throws AppException {

		final String uri = appConfig.getCloudantHost() + dbName + "/_all_docs?include_docs=true";
		logger.info("Inside the getAllCustomers " + uri);
		ResponseEntity<CustomerResponse> result = null;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			result = template.exchange(uri, HttpMethod.GET, entity, CustomerResponse.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("Http Exception while fetchfing customers records: {}", e.getResponseBodyAsString());
			ErrorUtil.handleExternalServiceError(e);

		}
		return result.getBody().getRows();
	}

	/**
	 * Method list single customer records from the database.
	 * @throws AppException
	 */

	public CustomerResponseDocs getCustomer(String id) throws AppException {

		final String uri = appConfig.getCloudantHost() + dbName + "/" + id;
		logger.info("Inside the getCustomer " + uri);
		CustomerResponseDocs customerResponseDocs = null;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<CustomerResponseDocs> result = template.exchange(uri, HttpMethod.GET, entity,
					CustomerResponseDocs.class);

			if (result != null && result.getStatusCode().equals(HttpStatus.OK) && result.getBody() != null) {
				customerResponseDocs = result.getBody();
			}

		} catch (HttpClientErrorException | HttpServerErrorException e) {

			logger.error("Http Exception while retriving customer records: {}", e.getResponseBodyAsString());
			ErrorUtil.handleExternalServiceError(e);

		}

		return customerResponseDocs;

	}

	/**
	 * Method persists new customer records in the database.
	 * @throws AppException
	 */

	public CustomerCreateResponse createCustomer(CreateCustomer createCustomer) throws AppException {

		final String uri = appConfig.getCloudantHost() + dbName;

		logger.info("Inside the createCustomer " + uri);
		ResponseEntity<CustomerCreateResponse> result = null;
		try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity;

			entity = new HttpEntity<String>(mapper.writeValueAsString(createCustomer), headers);
			result = template.exchange(uri, HttpMethod.POST, entity, CustomerCreateResponse.class);

		} catch (JsonProcessingException e) {

			logger.error("JsonProcessing Exception while creating customer records : {}", e.getStackTrace().toString());
		}
		catch (HttpClientErrorException | HttpServerErrorException e) {

			logger.error("Http Exception while creating new customer records {}", e.getResponseBodyAsString());
			ErrorUtil.handleExternalServiceError(e);

		}

		return result.getBody();
	}

	/**
	 * Method update customer records in the database.
	 * @throws AppException
	 */

	public CustomerCreateResponse updateCustomer(String id, String rev, UpdateCustomer updateCustomer)
			throws AppException {

		final String uri = appConfig.getCloudantHost() + dbName;
		ResponseEntity<CustomerCreateResponse> result = null;
		try {
		logger.info("Inside the updateCustomer " + uri);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity;

		updateCustomer.set_id(id);
		updateCustomer.set_rev(rev);

			entity = new HttpEntity<String>(mapper.writeValueAsString(updateCustomer), headers);
			result = template.exchange(uri, HttpMethod.POST, entity, CustomerCreateResponse.class);

		} catch (JsonProcessingException  e) {
			// TODO Auto-generated catch block
			logger.error("JsonProcessing Exception while updating customer records : {}", e.getStackTrace().toString());

		}catch (HttpClientErrorException | HttpServerErrorException e) {

			logger.error("Http Exception while updating customer records : {}", e.getResponseBodyAsString());
			ErrorUtil.handleExternalServiceError(e);

		}

		return result.getBody();
	}

	/**
	 * Method removes customer records from the database.
	 * @throws AppException
	 */

	public CustomerCreateResponse deleteCustomer(String id, String rev) throws AppException {

		final String uri = appConfig.getCloudantHost() + dbName + "/" + id + "?rev=" + rev;
		logger.info("Inside the deleteCustomer " + uri);
		ResponseEntity<CustomerCreateResponse> result =null;
		try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + appConfig.getCloudantPassword());
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		result = template.exchange(uri, HttpMethod.DELETE, entity,
				CustomerCreateResponse.class);
		}catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("Http Exception while deleting customer records: {}", e.getResponseBodyAsString());
			ErrorUtil.handleExternalServiceError(e);

		}
		return result.getBody();
	}
}
