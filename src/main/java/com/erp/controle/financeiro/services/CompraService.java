package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Compra;
import com.erp.controle.financeiro.repositories.CompraRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;
		
	public List<Compra> findAll(){
		return repository.findAll();		
	}
	
	public Compra findById(Long id) {
		Optional<Compra> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));		
	}
}