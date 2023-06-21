package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Fatura;
import com.erp.controle.financeiro.repositories.FaturaRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class FaturaService {
	
	@Autowired
	private FaturaRepository repository;

	public List<Fatura> findAll(){
		return repository.findAll();
	}
	
	public Fatura findById(Long id) {
		Optional<Fatura> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		
	}

}
