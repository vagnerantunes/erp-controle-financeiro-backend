package com.erp.controle.financeiro.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.controle.financeiro.entities.Venda;
import com.erp.controle.financeiro.services.VendaService;

//@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {

	@Autowired
	private VendaService service;

	@GetMapping
	public ResponseEntity<List<Venda>> findAll(){
		List<Venda> list = service.getAll();
		return ResponseEntity.ok().body(list);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Venda> findById(@PathVariable Long id) {
		Optional<Venda> venda = service.findById(id);
		return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	@PostMapping
	public ResponseEntity<Venda> insert(@RequestBody Venda obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}