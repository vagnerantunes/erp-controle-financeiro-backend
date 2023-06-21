package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.services.FormaPagamentoService;

@RestController
@RequestMapping(value = "/fpagamentos")
public class FormaPagamentoResource {
	
	@Autowired
	private FormaPagamentoService service;
	
	@GetMapping
	public ResponseEntity<List<FormaPagamento>> findAll(){
		List<FormaPagamento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FormaPagamento> findById(@PathVariable Long id){
		FormaPagamento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<FormaPagamento> insert(@RequestBody FormaPagamento obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getFpgId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
}
