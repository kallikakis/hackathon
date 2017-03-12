package com.doclerholding.hackaton.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = AbstractRestException.class)
	public ResponseEntity<String> handleRestException(AbstractRestException ex) {
		ResponseStatus rsAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);
		HttpStatus code = rsAnnotation == null ? HttpStatus.INTERNAL_SERVER_ERROR : rsAnnotation.code();
		return new ResponseEntity<>(ex.getResponse(), code);
	}

	@ExceptionHandler(value=Throwable.class)
	public ResponseEntity<String> handleOtherException(Throwable th) {
		LOGGER.error(th.getMessage(), th);
		return new ResponseEntity<>(th.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
