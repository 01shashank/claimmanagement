package com.hexaware.claimmanagement.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(ResourceNotFoundException exception,final HttpServletRequest request) {
		ExceptionResponse exp= new ExceptionResponse();
		exp.setErrormessage(exception.getMessage());
		exp.setRequestedURI(request.getRequestURI());
		
		return exp;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(response,HttpStatus.BAD_REQUEST);
	}
//	
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public @ResponseBody ExceptionResponse handleResourceNotFound(Exception exception,final HttpServletRequest request) {
//		ExceptionResponse exp = new ExceptionResponse();
//		exp.setErrormessage(exception.getMessage());
//		exp.setRequestedURI(request.getRequestURI());
//		
//		return exp;
//	}
//	
//	

}