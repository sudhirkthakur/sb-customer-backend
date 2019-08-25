package com.sb.demo.customer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



@Component
public class AppContextUtil implements ApplicationContextAware{
	
    private static final String WARN_MSG = "AppContextUtil application context not initialized";
    private static ApplicationContext context;
    private static final Logger logger = LoggerFactory.getLogger(AppContextUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;   
    }

    public static <T> T bean(Class<T> clazz) {
        if (context == null) {
        	logger.warn(WARN_MSG);
        	return null;
        }
        return context.getBean(clazz);
    }

    public static <T> T bean(String name) {
        if (context == null) {
        	logger.warn(WARN_MSG);
        	return null;
        }
        return (T) context.getBean(name);
    }

}
