package com.sb.demo.customer.models.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CustomerResponseRows {

	@ApiModelProperty(required = true, value = "id", example = "5")
	@JsonProperty("id")
	private String id;

	@ApiModelProperty(required = true, value = "key", example = "5")
	@JsonProperty("key")
	private String key;

	// @ApiModelProperty(required = true, value = "value", example = "5")
	// @JsonProperty("value")
	// private String value;
	//
	 @ApiModelProperty(required = true, value = "doc", example = "5")
	 @JsonProperty("doc")
	 private CustomerResponseDocs doc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	// public String getValue() {
	// return value;
	// }
	//
	// public void setValue(String value) {
	// this.value = value;
	// }
	//
//	 public String getDoc() {
//	 return doc;
//	 }
//	
//	 public void setDoc(String doc) {
//	 this.doc = doc;
//	 }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerResponse [id=");
		builder.append(id);
		builder.append(", key=");
		builder.append(key);
		// builder.append(", value=");
		// builder.append(value);
		 builder.append(", doc=");
		 builder.append(doc);
		builder.append("]");
		return builder.toString();
	}

	public CustomerResponseDocs getDoc() {
		return doc;
	}

	public void setDoc(CustomerResponseDocs doc) {
		this.doc = doc;
	}


}
