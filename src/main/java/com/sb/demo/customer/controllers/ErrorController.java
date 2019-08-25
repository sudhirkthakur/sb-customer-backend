package com.sb.demo.customer.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.responses.AppExceptionResponse;
import com.sb.demo.customer.services.impl.CustomerServiceImpl;
import com.sb.demo.customer.utils.Constants;
import com.sb.demo.customer.utils.MessageService;



@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@Autowired
	private MessageService messageService;
    
	/**
	 * Handles AppException 
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	
	@ExceptionHandler(value = { AppException.class })
	@ResponseBody
	protected ResponseEntity<AppExceptionResponse> handleAppException(AppException ex, WebRequest request) {

		AppExceptionResponse aeResponse = new AppExceptionResponse(ex.getUserMessage(), ex.getErrorCode(),
				new SimpleDateFormat(Constants.TIME_FORMAT).format(new Timestamp(System.currentTimeMillis())));
		logger.error("Error in App Exception is " + ex.getCause());
		
		return new ResponseEntity<>(aeResponse, ex.getHttpStatus());
	}
	
	/**
	 * Handles unknown Exception 
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	protected ResponseEntity<AppExceptionResponse> handleUnknownException(Exception ex, WebRequest request) {
		AppExceptionResponse aeResponse = new AppExceptionResponse(messageService.getMessage(Constants.CUST_200),
				Constants.CUST_200,
				new SimpleDateFormat(Constants.TIME_FORMAT).format(new Timestamp(System.currentTimeMillis())));
		logger.error("Error in Exception class is " + ex.getCause());
		return new ResponseEntity<>(aeResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
