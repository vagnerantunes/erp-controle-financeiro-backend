package com.erp.controle.financeiro.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.controle.financeiro.entities.Venda;
import com.erp.controle.financeiro.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {

	@Autowired
	private VendaService service;
	
	@GetMapping
	public ResponseEntity<List<Venda>> findAll(){
		List<Venda> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Venda> findById(@PathVariable Long id) {
		Venda obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}	
	
}
