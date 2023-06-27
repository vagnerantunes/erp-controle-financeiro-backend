package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.erp.controle.financeiro.entities.Cliente;
import com.erp.controle.financeiro.repositories.ClienteRepository;
import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findall() {
		return repository.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Cliente update(Long id, Cliente obj) {
		try {
			Cliente entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {			
			throw new ResourceNotFoundException(id);
		}		
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setCliNome(obj.getCliNome());
		entity.setCliTipo(obj.getCliTipo());
		entity.setCliCpfCnpj(obj.getCliCpfCnpj());
		entity.setCliDataNascimento(obj.getCliDataNascimento());
		entity.setCliContato(obj.getCliContato());
		entity.setCliEmail(obj.getCliEmail());
		entity.setCliRazaoSocial(obj.getCliRazaoSocial());
		entity.setCliNomeFantasia(obj.getCliNomeFantasia());
		entity.setCliEndereco(obj.getCliEndereco());
		entity.setCliSegmento(obj.getCliSegmento());
		entity.setCliFlag(obj.getCliFlag());
	}	

}