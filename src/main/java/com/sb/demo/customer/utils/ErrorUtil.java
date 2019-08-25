package com.sb.demo.customer.utils;

import java.io.IOException;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.responses.AppExceptionResponse;

@Component
public class ErrorUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorUtil.class);
	private HashMap<String, HttpStatus> errorResponseStatus;
	
	@Autowired
	public ErrorUtil() {
		init();		
	}
	
	private void init() {
		errorResponseStatus = new HashMap<String, HttpStatus>();
		errorResponseStatus.put(Constants.CUST_101, HttpStatus.BAD_REQUEST);
		errorResponseStatus.put(Constants.CUST_102, HttpStatus.UNAUTHORIZED);
		errorResponseStatus.put(Constants.CUST_103, HttpStatus.NOT_FOUND);
		errorResponseStatus.put(Constants.CUST_104, HttpStatus.FORBIDDEN);
		errorResponseStatus.put(Constants.CUST_105, HttpStatus.CONFLICT);
		errorResponseStatus.put(Constants.CUST_106, HttpStatus.NO_CONTENT);
		errorResponseStatus.put(Constants.CUST_110, HttpStatus.OK);
		errorResponseStatus.put(Constants.CUST_111, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static void handleExternalServiceError(Exception exception) throws AppException {

		String errorCode = null;
		if (exception instanceof HttpClientErrorException) {
			switch (((HttpStatusCodeException) exception).getStatusCode()) {
			case BAD_REQUEST:
				errorCode = Constants.CUST_101;
				break;
			case UNAUTHORIZED:
				errorCode = Constants.CUST_102;	
				break;
			case NOT_FOUND:
				errorCode = Constants.CUST_103;
				break;
			case FORBIDDEN:
				errorCode = Constants.CUST_104;
				break;
			case CONFLICT:
				errorCode = Constants.CUST_105;
				break;
			default:
				errorCode = Constants.CUST_106;
			}
			logger.error("HTTP Client error while connecting to cloudant database with status code {}",
					((HttpClientErrorException) exception).getStatusCode(), exception);
			String errorResponse = ((HttpClientErrorException) exception).getResponseBodyAsString();
			ObjectMapper mapper = new ObjectMapper();
			
			AppExceptionResponse appExceptionResponse = null;
			try {
				appExceptionResponse = mapper.readValue(errorResponse, AppExceptionResponse.class);
			} catch (IOException e1) {
				throw new AppException(exception, Constants.CUST_110);
			}
			throw new AppException(errorCode);

		} else if (exception instanceof HttpServerErrorException) {
			logger.error("HTTP Server error while connecting to cloudant database with status code {}",
					((HttpServerErrorException) exception).getStatusCode(), exception);
			throw new AppException(Constants.CUST_111);
		} 
		
		
		
	}
	
	public HttpStatus getHttpStatus(String errorCode) {

		HttpStatus httpStatus = errorResponseStatus.get(errorCode);
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.warn("Response HttpStatus Code not configured for Error Code: " + errorCode
					+ ". Defaulting it to INTERNAL_SERVER_ERROR.");
		}
		return httpStatus;
	}

	
	
}
