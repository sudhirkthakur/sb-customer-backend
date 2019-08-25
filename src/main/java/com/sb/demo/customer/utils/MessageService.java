package com.sb.demo.customer.utils;

import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public interface MessageService {
	
	public String getMessage(String code);
	public String getMessage(String code, Locale locale);
	public String getMessage(String code, Object[] args);
	public String getMessage(String code, Object[] args, Locale locale);

}
