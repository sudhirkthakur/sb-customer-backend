package com.sb.demo.customer.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CustomerAddress {
	
	@ApiModelProperty(required = true, value = "type", example = "Homne")
	@JsonProperty("type")
	private String type;

	@ApiModelProperty(required = true, value = "addrLine1", example = "Bangalore")
	@JsonProperty("addrLine1")
	private String addrLine1;

	@ApiModelProperty(required = true, value = "addrLine2", example = "kaggadaspura")
	@JsonProperty("addrLine2")
	private String addrLine2;

	@ApiModelProperty(required = true, value = "addrLine3", example = "C V Raman")
	@JsonProperty("addrLine3")
	private String addrLine3;

	@ApiModelProperty(required = true, value = "addrLine4", example = "Indira Nagar")
	@JsonProperty("addrLine4")
	private String addrLine4;

	@ApiModelProperty(required = true, value = "countryCode", example = "Ind")
	@JsonProperty("countryCode")
	private String countryCode;
	
	@ApiModelProperty(required = true, value = "zipCode", example = "59898")
	@JsonProperty("zipCode")
	private String zipCode;

	@ApiModelProperty(required = true, value = "state", example = "Ind")
	@JsonProperty("state")
	private String state;

	@ApiModelProperty(required = true, value = "city", example = "Bang")
	@JsonProperty("city")
	private String city;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerAddress [type=");
		builder.append(type);
		builder.append(", addrLine1=");
		builder.append(addrLine1);
		builder.append(", addrLine2=");
		builder.append(addrLine2);
		builder.append(", addrLine3=");
		builder.append(addrLine3);
		builder.append(", addrLine4=");
		builder.append(addrLine4);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", state=");
		builder.append(state);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getAddrLine3() {
		return addrLine3;
	}

	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	public String getAddrLine4() {
		return addrLine4;
	}

	public void setAddrLine4(String addrLine4) {
		this.addrLine4 = addrLine4;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
