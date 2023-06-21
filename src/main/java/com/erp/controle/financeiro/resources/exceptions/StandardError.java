package com.erp.controle.financeiro.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/* Nesta classe que pertence ao pacote resource, vamos tratar os erros por atributos.. 
 * para armazenar o momento em que deu o erro, status, qual tipo de erro.. 
 */
public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	
	@Getter
	@Setter
	private Integer status;
	
	@Getter
	@Setter
	private String error;
	
	@Getter
	@Setter
	private String message;
	
	@Getter
	@Setter	
	private String path;
	
	public StandardError() {
		
	}

	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}	
}