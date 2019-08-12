package com.sb.demo.customer.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;

public interface CustomerService {
	
	public List<CustomerResponseRows> getAllCustomers();
	
	public CustomerResponseDocs getCustomer(String id) ;
	
    public CustomerCreateResponse createCustomer(CreateCustomer createCustomer);
	
    public CustomerCreateResponse updateCustomer(String id,String rev,CreateCustomer createCustomer);
	
    public CustomerCreateResponse deleteCustomer(String id,String rev);


}
