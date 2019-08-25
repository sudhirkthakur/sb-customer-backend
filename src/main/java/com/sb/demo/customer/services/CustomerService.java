package com.sb.demo.customer.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.request.UpdateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;

public interface CustomerService {
	
	public List<CustomerResponseRows> getAllCustomers() throws AppException;
	
	public CustomerResponseDocs getCustomer(String id) throws AppException;
	
    public CustomerCreateResponse createCustomer(CreateCustomer createCustomer) throws AppException;
	
    public CustomerCreateResponse updateCustomer(String id,String rev,UpdateCustomer updateCustomer) throws AppException;
	
    public CustomerCreateResponse deleteCustomer(String id,String rev) throws AppException;


}
