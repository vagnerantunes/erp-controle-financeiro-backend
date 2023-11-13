package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.erp.controle.financeiro.dto.FornecedorDTO;
import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.services.FornecedorService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorService service;

	@Autowired
	public FornecedorResource(FornecedorService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> getAllFornecedors() {
		List<FornecedorDTO> products = service.getAllFornecedors();
		return ResponseEntity.ok(products);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<FornecedorDTO>> getAllFornecedorsPage(@RequestParam(defaultValue = "0") int pageNumber,
															   @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "forId") String sortBy,
															   @RequestParam(defaultValue = "asc") String sortOrder) {

		Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

		Page<FornecedorDTO> products = service.getAllFornecedorsPage(pageable);
		return ResponseEntity.ok(products);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
		Fornecedor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody FornecedorNewDTO objDto) {
		Fornecedor obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getForId()).toUri();
		return ResponseEntity.created(uri).build();
	}

//	@PostMapping
//	public ResponseEntity<FornecedorDTO> addFornecedor(@RequestBody FornecedorDTO productDTO) {
//		FornecedorDTO createdFornecedor = service.addFornecedor(productDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdFornecedor);
//	}

//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Void> update(@Valid @RequestBody FornecedorDTO objDto, @PathVariable Long id) {
//		Fornecedor obj = service.fromDTO(objDto);
//		obj.setForId(id);
//		obj = service.update(id, obj);
//		return ResponseEntity.noContent().build();
//	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody FornecedorNewDTO objDto, @PathVariable Long id) {
		service.update(id, objDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
		service.deleteFornecedor(id);
		return ResponseEntity.noContent().build();
	}

}
