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

import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.request.CreateCustomer;
import com.sb.demo.customer.models.request.UpdateCustomer;
import com.sb.demo.customer.models.responses.CustomerCreateResponse;
import com.sb.demo.customer.models.responses.CustomerResponseDocs;
import com.sb.demo.customer.models.responses.CustomerResponseRows;
import com.sb.demo.customer.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

	/**
	 * This method fetches the entire customer records
	 *	
	 * @param 
	 * @return
	 * @throws AppException
	 */

	@ApiOperation(value = "Fetches customer records",notes = "Fetches customer records")
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerResponseRows> getAllCustomers() throws AppException{
        return customerService.getAllCustomers();
	}

	
	/**
	 * This method fetches single customer records
	 *	
	 * @param customer id
	 * @return
	 * @throws AppException
	 */

	@ApiOperation(value = "Fetches single customer record",notes = "Fetches single customer record")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerResponseDocs getCustomer(@PathVariable String id) throws AppException{
		return customerService.getCustomer(id);
	}
	
	/**
	 * This method creates a new customer record
	 *	
	 * @param customer json object 
	 * @return
	 * @throws AppException
	 */

	@ApiOperation(value = "Creates a new customer record",notes = "Creates a new customer record")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.POST)
    public CustomerCreateResponse createCustomer(@ApiParam(value = "Create Customer",required = true) @RequestBody @Valid 
    		CreateCustomer createCustomer) throws AppException{
		return customerService.createCustomer(createCustomer);
	}

	/**
	 * This method updates customer record
	 *	
	 * @param customer json object
	 * @param customer id 
	 * @param customer records revision number 
	 * @return
	 * @throws AppException
	 */
	
	@ApiOperation(value = "Updates customer record",notes = "Updates customer record")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public CustomerCreateResponse updateCustomer(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "rev", required = true) String rev,@ApiParam(value = "Update Customer",required = true) @RequestBody @Valid 
			UpdateCustomer updateCustomer) throws AppException{
		return customerService.updateCustomer(id,rev,updateCustomer);
	}

	/**
	 * This method delete a customer record
	 *	
	 * @param customer id 
	 * @param customer records revision number 
	 * @return
	 * @throws AppException
	 */

	@ApiOperation(value = "Deletes customer record",notes = "Deletes customer record")
	@RequestMapping(value = "/customer", method = RequestMethod.DELETE)
    public CustomerCreateResponse deleteCustomer(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "rev", required = true) String rev) throws AppException{
		return customerService.deleteCustomer(id,rev);
	}

}
