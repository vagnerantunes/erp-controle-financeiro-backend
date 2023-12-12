package com.erp.controle.financeiro.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.erp.controle.financeiro.services.exceptions.ValueBigForAtributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, 
			HttpServletRequest request){

	String error = "Resource not found";
	HttpStatus status = HttpStatus.NOT_FOUND;
	StandardError err = new StandardError(Instant.now(), status.value(), error, 
			e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	
    }
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}

	@ExceptionHandler(ValueBigForAtributeException.class)
	public ResponseEntity<StandardError> database(ValueBigForAtributeException e, HttpServletRequest request){
		String error = "Erro de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}



}