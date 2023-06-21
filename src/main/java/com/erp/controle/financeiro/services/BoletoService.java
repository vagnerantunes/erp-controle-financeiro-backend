package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Boleto;
import com.erp.controle.financeiro.repositories.BoletoRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class BoletoService {
	
	@Autowired
	private BoletoRepository repository;
		
	public List<Boleto> findAll(){
		return repository.findAll();
	}	
	
	public Boleto findById(Long id) {
		Optional<Boleto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));		
	}
	
	public Boleto insert(Boleto obj) {
		return repository.save(obj);
	}
}