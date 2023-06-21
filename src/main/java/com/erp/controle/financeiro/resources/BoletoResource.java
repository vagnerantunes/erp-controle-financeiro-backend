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

import com.erp.controle.financeiro.entities.Boleto;
import com.erp.controle.financeiro.services.BoletoService;

@RestController
@RequestMapping(value = "/boletos")
public class BoletoResource {

	@Autowired
	private BoletoService service;
	
	@GetMapping
	public ResponseEntity<List<Boleto>> findAll(){
		List<Boleto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Boleto> findById(@PathVariable Long id){
		Boleto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<Boleto> insert(@RequestBody Boleto obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getBolId()).toUri();
		return ResponseEntity.created(uri).body(obj);		
	}
}