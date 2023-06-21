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

import com.erp.controle.financeiro.entities.Parcela;
import com.erp.controle.financeiro.services.ParcelaService;

@RestController
@RequestMapping(value = "/parcelas")
public class ParcelaResource {
	
	@Autowired
	private ParcelaService service;
	
	@GetMapping
	public ResponseEntity<List<Parcela>> findAll(){
		List<Parcela> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Parcela> findById(@PathVariable Long id){
		Parcela obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<Parcela> insert(@RequestBody Parcela obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getPVD_ID()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}