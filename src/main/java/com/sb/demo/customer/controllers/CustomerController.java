package com.sb.demo.customer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;
import com.sb.demo.customer.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;


/**
 * Controller for all Customer related CRUD operation.
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController  
@Api(tags = "Customers", value = "Customers Endpoints", description = "Operations for Customers CRUD OPeration.")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {

		this.customerService = customerService;
	}

	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerResponseRows> getAllCustomers(){
        return customerService.getAllCustomers();
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerResponseDocs getCustomer(@PathVariable String id){
		return customerService.getCustomer(id);
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.POST)
    public CustomerCreateResponse createCustomer(@ApiParam(value = "Create Customer",required = true) @RequestBody @Valid 
    		CreateCustomer createCustomer){
		return customerService.createCustomer(createCustomer);
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public CustomerCreateResponse updateCustomer(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "rev", required = true) String rev,@ApiParam(value = "Update Customer",required = true) @RequestBody @Valid 
    		CreateCustomer createCustomer){
		return customerService.updateCustomer(id,rev,createCustomer);
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.DELETE)
    public CustomerCreateResponse deleteCustomer(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "rev", required = true) String rev){
		return customerService.deleteCustomer(id,rev);
	}

}
