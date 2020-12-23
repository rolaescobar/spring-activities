package com.codigoit.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice 
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= {RuntimeException.class})
	@ResponseBody
	protected ResponseEntity<MensajeError> handleConflict (RuntimeException e, WebRequest request) { 
		
		MensajeError error = new MensajeError(e.getMessage(),e.getCause().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
