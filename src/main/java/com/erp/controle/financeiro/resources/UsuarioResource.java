package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.dto.UsuarioDTO;
import com.erp.controle.financeiro.entities.Usuario;
import com.erp.controle.financeiro.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}	
	
	//metodo utilizado para retornar resposta HTTP 201 - ok.
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getUSU_ID()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
	
	/*
	 * Metodo utilizado para inserir usuários no banco. Porem com esse metodo é retornado a mensagem de número 200 - OK,
	 * e o ideal é que se retorne o 201 - OK. Que é o padrão do HTTP
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);		
	}  
	 */
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}		
}