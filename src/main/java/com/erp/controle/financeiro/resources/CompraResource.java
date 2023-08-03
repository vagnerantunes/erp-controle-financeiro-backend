package com.erp.controle.financeiro.resources;

import java.util.List;

import com.erp.controle.financeiro.dto.CompraDTO;
import com.erp.controle.financeiro.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/compras")
public class CompraResource {

	@Autowired
	private CompraService service;

	@Autowired
	public CompraResource(CompraService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<CompraDTO>> getAllCompras() {
		List<CompraDTO> products = service.getAllCompras();
		return ResponseEntity.ok(products);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CompraDTO>> getAllComprasPage(@RequestParam(defaultValue = "0") int pageNumber,
															   @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "comId") String sortBy,
															   @RequestParam(defaultValue = "asc") String sortOrder) {

		Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

		Page<CompraDTO> products = service.getAllComprasPage(pageable);
		return ResponseEntity.ok(products);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CompraDTO> getCompraById(@PathVariable Long id) {
		return service.getCompraById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<CompraDTO> addCompra(@RequestBody CompraDTO productDTO) {
		CompraDTO createdCompra = service.addCompra(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCompra);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCompra(@PathVariable Long id, @RequestBody CompraDTO productDTO) {
		boolean updated = service.updateCompra(id, productDTO);
		if (updated) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
		service.deleteCompra(id);
		return ResponseEntity.noContent().build();
	}

}