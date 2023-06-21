package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Usuario;
import com.erp.controle.financeiro.repositories.UsuarioRepository;
import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
		
	@Autowired
	private UsuarioRepository repository;
	
	//listar todos usuarios
	public List<Usuario> findAll(){
		return repository.findAll(); 
	}
	
	/**Buscar usuario por codigo. Nesse metodo utilizando o obj.get() da o erro 500,  
	 ** caso a gente busque por um codigo inexistente **
	   public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.get();		
	   }	  
	 */
	
	/*com a utilização desse metedo com orElse.. ele tenta dar o get, se não tiver usuario
	 * lança a exceção
	 */
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		
	}
	
	//inserir usuario
	public Usuario insert(Usuario obj) {
		return repository.save(obj);
		
	}
	
	/* deletar usuario. retorna erro 500.
	 public void delete(Long id) {
		repository.deleteById(id);
	}
	 */
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}
		/*  Exceção para codigo inexistente.
		 *  Pegamos o ERRO "EmptyResultDataAccessException" gerado no console quando   
		 *  efetuamos a busca pela postman, e depois tratamos diretamente com ele no 
		 *  catch
		 */
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		/* Exceção para delete no qual possui pedidos...
		 * 
		 * No codigo abaixo pegamos DataIntegrityViolationException, 
		 * e dps lançamos uma exceção com a classe DatabaseException que foi criada
		 * no pacote services.exceptions
		 */		
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} 
		/* Esse catch é usado para capturar uma exceção e imprimir no console o nome da
		 * mesma. Como no caso das exceções acima EmptyResultDataAccessException e 
		 * DataIntegrityViolationException. A partir desses nomes gerado é possivel 
		 * fazer uma exceção manualmente com dados feitos por nós e nao que está sendo 
		 * gerado pelo proprio sistema. Apos capturar o erro com runtime que é generico 
		 * demais trocar pelo erro especifico que está sendo gerado.
		 * 
		 * CODIGO PARA CAPCTURAR ERRO.
		  	catch (RuntimeException e) {
		 
			e.printStackTrace();
			}
		 * 
		 */			
		
	}
	
	//atualizar usuario
	public Usuario update(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {			
			throw new ResourceNotFoundException(id);
		}		
	}

	private void updateData(Usuario entity, Usuario obj) {
		entity.setUSU_NOME(obj.getUSU_NOME());
		entity.setUSU_FLAG(obj.getUSU_FLAG());
		entity.setUSU_FUNCAO(obj.getUSU_FUNCAO());
	}	
	
}