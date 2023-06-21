package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.FornecedorRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repository;
	
	public List<Fornecedor> findAll(){
		return repository.findAll();
	}
	
	public Fornecedor findById(Long id) {
		Optional<Fornecedor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));		
	}
	
	public Fornecedor insert(Fornecedor obj) {
		return repository.save(obj);
	}
	
}
