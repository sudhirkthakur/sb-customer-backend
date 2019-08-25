package com.sb.demo.customer.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.request.UpdateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;
import com.sb.demo.customer.services.CustomerService;



public class CustomerControllerTest {
	
	private CustomerController controller;

	@Mock
	private CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new CustomerController(customerService);
	}
	
	 @Test
	 public void getAllCustomers() throws AppException {

		List<CustomerResponseRows> response = controller.getAllCustomers();	        
		verify(customerService).getAllCustomers();
//		assertEquals(HttpStatus.OK, response.getStatusCode());
	 }


	 @Test
	 public void getCustomer() throws AppException {

		CustomerResponseDocs response = controller.getCustomer("123");      
		verify(customerService).getCustomer("123");
//		assertEquals(HttpStatus.OK, response.getStatusCode());
	 }

	 
	 @Test
	 public void createCustomer() throws AppException {
		 
		 CreateCustomer createCustomer = new CreateCustomer();
		 
		 createCustomer.setFirstName("Raj");
		 createCustomer.setMiddleName("Kumar");

		 CustomerCreateResponse response = controller.createCustomer(createCustomer);      
		verify(customerService).createCustomer(createCustomer);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
	 }
	 
	 
	 @Test
	 public void updateCustomer() throws AppException {
		 
		 UpdateCustomer updateCustomer = new UpdateCustomer();
		 updateCustomer.setFirstName("Raj");
		 updateCustomer.setMiddleName("Kumar");
		 CustomerCreateResponse response = controller.updateCustomer("1111", "2222", updateCustomer);     
		verify(customerService).updateCustomer("1111", "2222", updateCustomer);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
	 }
	 
	 @Test
	 public void deleteCustomer() throws AppException {

		 CustomerCreateResponse response = controller.deleteCustomer("111", "55555");      
		verify(customerService).deleteCustomer("111", "55555");
//		assertEquals(HttpStatus.OK, response.getStatusCode());
	 }
	 
}
