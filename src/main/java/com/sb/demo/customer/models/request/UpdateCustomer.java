package com.sb.demo.customer.models.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sb.demo.customer.models.responses.CustomerResponseDocsAddress;

import io.swagger.annotations.ApiModelProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)


public class UpdateCustomer {
	

	@ApiModelProperty(required = true, value = "_id", example = "Raju")
	@JsonProperty("_id")
	private String _id;

	@ApiModelProperty(required = true, value = "_rev", example = "Kumar")
	@JsonProperty("_rev")
	private String _rev;

	
	
	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public String get_rev() {
		return _rev;
	}



	public void set_rev(String _rev) {
		this._rev = _rev;
	}



	@ApiModelProperty(required = true, value = "firstName", example = "Raju")
	@JsonProperty("firstName")
	private String firstName;

	@ApiModelProperty(required = true, value = "middleName", example = "Kumar")
	@JsonProperty("middleName")
	private String middleName;

	@ApiModelProperty(required = true, value = "lastName", example = "Sinha")
	@JsonProperty("lastName")
	private String lastName;

	@ApiModelProperty(required = true, value = "dateOfBirth", example = "10/10/10")
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;

	@ApiModelProperty(required = true, value = "mobileNumber", example = "55555555555555")
	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@ApiModelProperty(required = true, value = "gender", example = "M")
	@JsonProperty("gender")
	private String gender;

	@ApiModelProperty(required = true, value = "customerNnumber", example = "88888888888")
	@JsonProperty("customerNnumber")
	private String customerNnumber;

	@ApiModelProperty(required = true, value = "countryOfBirth", example = "India")
	@JsonProperty("countryOfBirth")
	private String countryOfBirth;

	@ApiModelProperty(required = true, value = "countryofResidence", example = "India")
	@JsonProperty("countryofResidence")
	private String countryofResidence;

	@ApiModelProperty(required = true, value = "customerSegment", example = "IT")
	@JsonProperty("customerSegment")
	private String customerSegment;


	
	
	
	@ApiModelProperty(required = true, value = "addresses")
	@JsonProperty("addresses")
	private List<CustomerAddress> addresses;



	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateCustomer [firstName=");
		builder.append(firstName);
		builder.append(", _id=");
		builder.append(_id);
		builder.append(", _rev=");
		builder.append(_rev);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", customerNnumber=");
		builder.append(customerNnumber);
		builder.append(", countryOfBirth=");
		builder.append(countryOfBirth);
		builder.append(", countryofResidence=");
		builder.append(countryofResidence);
		builder.append(", customerSegment=");
		builder.append(customerSegment);
		builder.append(", addresses=");
		builder.append(addresses);
		builder.append("]");
		return builder.toString();
	}



	public List<CustomerAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CustomerAddress> addresses) {
		this.addresses = addresses;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getMiddleName() {
		return middleName;
	}



	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getCustomerNnumber() {
		return customerNnumber;
	}



	public void setCustomerNnumber(String customerNnumber) {
		this.customerNnumber = customerNnumber;
	}



	public String getCountryOfBirth() {
		return countryOfBirth;
	}



	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}



	public String getCountryofResidence() {
		return countryofResidence;
	}



	public void setCountryofResidence(String countryofResidence) {
		this.countryofResidence = countryofResidence;
	}



	public String getCustomerSegment() {
		return customerSegment;
	}



	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}




}
