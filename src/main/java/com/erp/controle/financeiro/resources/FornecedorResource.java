package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;

import com.erp.controle.financeiro.dto.FornecedorDTO;
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

	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDTO> getFornecedorById(@PathVariable Long id) {
		return service.getFornecedorById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<FornecedorDTO> addFornecedor(@RequestBody FornecedorDTO productDTO) {
		FornecedorDTO createdFornecedor = service.addFornecedor(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdFornecedor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateFornecedor(@PathVariable Long id, @RequestBody FornecedorDTO productDTO) {
		boolean updated = service.updateFornecedor(id, productDTO);
		if (updated) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
		service.deleteFornecedor(id);
		return ResponseEntity.noContent().build();
	}

}
