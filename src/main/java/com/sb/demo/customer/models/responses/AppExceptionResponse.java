package com.sb.demo.customer.models.responses;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AppExceptionResponse {
	
	@ApiModelProperty( value = "Exception Message")
	private String message;
	@ApiModelProperty(value = "Error Code")
	private String code;
	@ApiModelProperty( value = "timestamp ")
	private String timestamp;

	
	public AppExceptionResponse(String message, String code, String timestamp) {
		super();
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
	}
	public AppExceptionResponse() {
		
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("message", "" + message);
		builder.add("code", code);
		builder.add("timestamp", timestamp);

		JsonObject errorSchema = builder.build();
		return errorSchema.toString();
	}

}
