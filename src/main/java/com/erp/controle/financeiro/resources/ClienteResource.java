package com.erp.controle.financeiro.resources;

import com.erp.controle.financeiro.dto.ClienteNewDTO;
import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.entities.Cliente;
import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.services.ClienteService;
import com.erp.controle.financeiro.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteNewDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteNewDTO> listDto = list.stream().map(obj -> service.toNewDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Cliente>> getAllPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                                  @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "cliId") String sortBy,
                                                                  @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        Page<Cliente> clientePage = service.getAllPage(pageable);
        return ResponseEntity.ok(clientePage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteNewDTO> findById(@PathVariable Long id) {
        Cliente obj = service.findById(id);
        ClienteNewDTO dto = service.toNewDTO(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
        Cliente obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCliId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteNewDTO objDto, @PathVariable Long id) {
        service.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}