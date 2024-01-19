//package com.erp.controle.financeiro.resources;
//
//import java.net.URI;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.erp.controle.financeiro.dto.FornecedorNewDTO;
//import com.erp.controle.financeiro.dto.UsuarioDTO;
//import com.erp.controle.financeiro.entities.Fornecedor;
//import com.erp.controle.financeiro.services.UsuarioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.erp.controle.financeiro.dto.UsuarioDTO;
//import com.erp.controle.financeiro.entities.Usuario;
//import com.erp.controle.financeiro.services.UsuarioService;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping(value = "/usuarios")
//public class UsuarioResource {
//
//	@Autowired
//	private UsuarioService service;
//
////	@Autowired
////	public UsuarioResource(UsuarioService service) {
////		this.service = service;
////	}
//
//	@GetMapping
//	public ResponseEntity<List<UsuarioDTO>> findAll() {
//		List<Usuario> list = service.findAll();
//		List<UsuarioDTO> listDto = list.stream().map(obj -> service.toNewDTO(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}
//
//	@RequestMapping(value = "/page", method = RequestMethod.GET)
//	public ResponseEntity<Page<Usuario>> getAllUsuariosPage(@RequestParam(defaultValue = "0") int pageNumber,
//																  @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "usuId") String sortBy,
//																  @RequestParam(defaultValue = "asc") String sortOrder) {
//
//		Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));
//
//		Page<Usuario> usuarioPage = service.getAllUsuariosPage(pageable);
//		return ResponseEntity.ok(usuarioPage);
//	}
//
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
//		Usuario obj = service.findById(id);
//		UsuarioDTO dto = service.toNewDTO(obj);
//		return ResponseEntity.ok().body(dto);
//	}
//
//	@PostMapping
//	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDto) {
//		Usuario obj = service.fromDTO(objDto);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getUsuId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
//
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Long id) {
//		service.update(id, objDto);
//		return ResponseEntity.noContent().build();
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
//		service.deleteUsuario(id);
//		return ResponseEntity.noContent().build();
//	}
//}