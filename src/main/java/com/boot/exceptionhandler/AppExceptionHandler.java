package com.boot.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	String handleException(Exception e) {
		logger.error("Exception occurred", e);
		return "error";

	}
}
