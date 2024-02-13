package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.dto.ProdutoDTO;
import com.erp.controle.financeiro.entities.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.services.ProdutoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<Produto> list = service.findAll();
		List<ProdutoDTO> listDto = list.stream().map(obj -> service.toNewDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		Produto obj = service.findById(id);
		ProdutoDTO dto = service.toNewDTO(obj);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO objDto) {
		Produto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getProId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO objDto, @PathVariable Long id) {
		service.update(id, objDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}