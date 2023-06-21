package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Venda;
import com.erp.controle.financeiro.repositories.VendaRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll(){
		return repository.findAll();
	}
	
	public Venda findById(Long id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));		
	}	
	
}