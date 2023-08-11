package com.telstra.codechallenge.oldestusers.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@RestControllerAdvice
public class GlobalResponseExceptionHandler {

	 private final Logger log= LoggerFactory.getLogger(GlobalResponseExceptionHandler.class);
	 private static final String EXCEPTION = " Exception{} ";
	 // Handle specific Exceptions
	 
	 //Handle User Not Found Exception
	 @ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception,
			HttpServletResponse response, HttpServletRequest request){
		 log.warn(EXCEPTION, exception);
		 ResponseErrorMessage errorMsg= new ResponseErrorMessage(HttpStatus.NOT_FOUND.value(),
				 HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
				return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
	 }
	 
	 //Global Exception Handler
	 //Handle Non-specified Exception
	 @ExceptionHandler(ResponseStatusException.class)
		public ResponseEntity<Object> handleNoHandlerException(ResponseStatusException exception,
				HttpServletResponse response, HttpServletRequest request){
			 log.warn(EXCEPTION, exception);
			 ResponseErrorMessage errorMsg= new ResponseErrorMessage(HttpStatus.NOT_FOUND.value(),
					 HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
						return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);					
		 }
	 
	 //Handle HttpClientErrorException
	 @ExceptionHandler(value= {HttpClientErrorException.class, IllegalArgumentException.class})
		public ResponseEntity<Object> handleHttpException(HttpClientErrorException exception,
				HttpServletResponse response, HttpServletRequest request){
			 log.warn(EXCEPTION, exception);
			 ResponseErrorMessage errorMsg= new ResponseErrorMessage(HttpStatus.BAD_REQUEST.value(),
					 HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage());
				return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
		 }
	 
	 //Handle Forbidden Exception
	 @ExceptionHandler(Forbidden.class)
		public ResponseEntity<Object> handleForbiddenException(Forbidden exception,
				HttpServletResponse response, HttpServletRequest request){
			 log.warn(EXCEPTION, exception);
			 ResponseErrorMessage errorMsg= new ResponseErrorMessage(HttpStatus.FORBIDDEN.value(),
					 HttpStatus.FORBIDDEN.getReasonPhrase(),exception.getMessage());
				return new ResponseEntity<>(errorMsg, HttpStatus.FORBIDDEN);
		 }
	 
	 
}
