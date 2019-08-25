package com.sb.demo.customer.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;



@Component
public class MessageServiceProvider implements MessageService{
	
	@Autowired
	private MessageSource messageSource;

	private static final String DEBUG = "DEBUG.";
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceProvider.class);

	public MessageServiceProvider() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage(String code) {
		return getSourceMessage(code, null, LocaleContextHolder.getLocale());
	}

	public String getMessage(String code, Object[] args) {
		return getSourceMessage(code, args, LocaleContextHolder.getLocale());
	}

	public String getMessage(String code, Locale locale) {
		return getSourceMessage(code, null, locale);
	}

	/**
	 * lookup the message from resource bundle.
	 * 
	 */
	public String getMessage(String code, Object[] args, Locale locale) {
		return getSourceMessage(code, args, locale);
	}

	
	private String getSourceMessage(String code, Object[] args, Locale locale) {
		String message;  
		try {
			message = messageSource.getMessage(code,args,locale);
		} catch(NoSuchMessageException e) {
			logger.warn(e.getMessage());
			message = e.getMessage();
		}
		return message;
	}
	

}
