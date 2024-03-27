package com.erp.controle.financeiro.resources;

import java.util.List;
import java.util.Optional;

import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.services.ProdutoService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> list = service.getAll();
		return ResponseEntity.ok().body(list);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Optional<Produto> produto = service.findById(id);
		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PostMapping
	public ResponseEntity<Produto> insert(@RequestBody Produto obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}