package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.UsuarioDTO;
import com.erp.controle.financeiro.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.dto.UsuarioDTO;
import com.erp.controle.financeiro.entities.Usuario;
import com.erp.controle.financeiro.services.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@Autowired
	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
		List<UsuarioDTO> products = service.getAllUsuarios();
		return ResponseEntity.ok(products);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> getAllUsuariosPage(@RequestParam(defaultValue = "0") int pageNumber,
															   @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "usuId") String sortBy,
															   @RequestParam(defaultValue = "asc") String sortOrder) {

		Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

		Page<UsuarioDTO> products = service.getAllUsuariosPage(pageable);
		return ResponseEntity.ok(products);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
		return service.getUsuarioById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO productDTO) {
		UsuarioDTO createdUsuario = service.addUsuario(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO productDTO) {
		boolean updated = service.updateUsuario(id, productDTO);
		if (updated) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		service.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}	
}