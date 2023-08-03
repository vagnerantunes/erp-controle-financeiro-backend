package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;

import com.erp.controle.financeiro.dto.BoletoDTO;
import com.erp.controle.financeiro.services.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.entities.Boleto;
import com.erp.controle.financeiro.services.BoletoService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/boletos")
public class BoletoResource {

	@Autowired
	private BoletoService service;

	@Autowired
	public BoletoResource(BoletoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<BoletoDTO>> getAllBoletos() {
		List<BoletoDTO> products = service.getAllBoletos();
		return ResponseEntity.ok(products);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<BoletoDTO>> getAllBoletosPage(@RequestParam(defaultValue = "0") int pageNumber,
															   @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "bolId") String sortBy,
															   @RequestParam(defaultValue = "asc") String sortOrder) {

		Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

		Page<BoletoDTO> products = service.getAllBoletosPage(pageable);
		return ResponseEntity.ok(products);

	}

	@GetMapping("/{id}")
	public ResponseEntity<BoletoDTO> getBoletoById(@PathVariable Long id) {
		return service.getBoletoById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<BoletoDTO> addBoleto(@RequestBody BoletoDTO productDTO) {
		BoletoDTO createdBoleto = service.addBoleto(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBoleto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateBoleto(@PathVariable Long id, @RequestBody BoletoDTO productDTO) {
		boolean updated = service.updateBoleto(id, productDTO);
		if (updated) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBoleto(@PathVariable Long id) {
		service.deleteBoleto(id);
		return ResponseEntity.noContent().build();
	}

}