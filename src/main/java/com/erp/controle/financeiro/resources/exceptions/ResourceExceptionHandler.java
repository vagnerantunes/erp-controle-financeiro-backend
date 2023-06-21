package com.erp.controle.financeiro.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

/* Nesta classe resource vamos tratar como será exibido os possiveis erros. Por exemplo o 
 * erro ResourceNotFoundException que foi gerado quando tentamos buscar um codigo em 
 * findbyid inexistente, será feito um metodo para tratarmos esse erro. E nesse metodo
 * vamos instanciar a classe ResourceNotFoundException, StandardError e assim tratar o erro
 * de maneira adequando e especifica, não deixando o sistema exibir erros internos para o 
 * usuário.
 */

//essa anotação ira interceptar a exeções que acontecer para que esse objeto possa executar um 
//possivel tratamento
@ControllerAdvice
public class ResourceExceptionHandler {
	
	//anotação para que faça a captação do erro e volte para esse metodo... 
	//metodo para tratamentoo do erro relacionado a codigo inexistente para busca 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, 
			HttpServletRequest request){
		
	/* Está sendo declarado os atributos error e status, para que no construtor 
	 * StandarError possamos passar os valores para ele.. 
	 *  sendo que  Instant.now é o momento que aconteceu o erro, status do erro que 
	 *  foi atribuido o .value que passa para inteiro o valor e impedi erros, error 
	 *  mensagem que passamos no atributo string, e será a mensagem que declaramos no 
	 *  metodo ResourceNotFoundException na classe ResourceNotFoundException que está no 
	 *  pacote service, e o caminho pegamos com HttpServletRequest e passamos com o objeto
	 *  request declarado.
	 */
	String error = "Resource not found";
	HttpStatus status = HttpStatus.NOT_FOUND;
	StandardError err = new StandardError(Instant.now(), status.value(), error, 
			e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	
    }
	
	//exceção para erro relacionado a codigo contendo pedido para deletar
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;		
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
}