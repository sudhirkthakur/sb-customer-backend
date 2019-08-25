package com.sb.demo.customer.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sb.demo.customer.utils.AppContextUtil;
import com.sb.demo.customer.utils.ErrorUtil;
import com.sb.demo.customer.utils.MessageService;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AppException extends Exception{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AppException.class);
	private String errorCode;
	private Object[] userMessageParams;
	private HttpStatus httpStatus; 
	private Exception sourceException;
	private String userMessage;
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private ErrorUtil errorUtil;

	
	public AppException() {
	}


	/**
	 * Constructor for creating AppException where source exception userParams are null.
	 * @param errorCode
	 *            
	 */

	public AppException(String errorCode) {
		this.errorCode = errorCode;
		init();
	}

	/**
	 * Constructor for creating AppException from existing Source Exception.
	 * @param errorCode
	 *            the key to lookup up, such as 'no.resource.found'
	 */
	public AppException(Exception sourceException, String errorCode) {
		this.setSourceException(sourceException);
		this.errorCode = errorCode;
		init();
	}


	
	public String getLocalizedMessage() {
		if (userMessage!=null) {
			return userMessage;
		} else if (messageService != null) {
			return messageService.getMessage(errorCode, userMessageParams);
		} else {
			logger.warn("MessageService is Null");
			return "";
		}
	} 

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		if (userMessage!=null) {
			return userMessage;
		} else if (messageService != null) {
			return messageService.getMessage(errorCode, userMessageParams, Locale.US);
		} else {
			logger.warn("MessageService is Null");
			return "";
		}
	} 

	public HttpStatus getHttpStatus() {
		if (httpStatus!=null) {
			return httpStatus;
		} else if (errorUtil!=null) {
			return errorUtil.getHttpStatus(errorCode);
		} else {
			logger.warn("ErrorUtil is null. Defaulting Error Response HttpStatus code to INTERNAL_SERVER_ERROR.");
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	private void init() {
		Object obj = AppContextUtil.bean(MessageService.class);
		if (obj != null) {
			messageService = (MessageService) AppContextUtil.bean(MessageService.class);
		}
		obj = AppContextUtil.bean(ErrorUtil.class);
		if (obj != null) {
			errorUtil = (ErrorUtil) AppContextUtil.bean(ErrorUtil.class);
		}
	}

	public Object[] getUserMessageParams() {
		return userMessageParams;
	}

	public void setUserMessageParams(Object[] userMessageParams) {
		this.userMessageParams = userMessageParams;
	}
	public Exception getSourceException() {
		return sourceException;
	}

	public void setSourceException(Exception sourceException) {
		this.sourceException = sourceException;
	}
	public String getUserMessage() {
		if (userMessage==null) {
			return getLocalizedMessage();
		}
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
}
