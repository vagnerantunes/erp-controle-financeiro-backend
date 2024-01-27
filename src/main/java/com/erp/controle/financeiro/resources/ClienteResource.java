package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;

import com.erp.controle.financeiro.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.controle.financeiro.entities.Cliente;
import com.erp.controle.financeiro.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @Autowired
    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> products = service.getAllClientes();
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> getAllClientesPage(@RequestParam(defaultValue = "0") int pageNumber,
           @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "cliId") String sortBy,
           @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        Page<ClienteDTO> products = service.getAllClientesPage(pageable);
        return ResponseEntity.ok(products);

    }

    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        return service.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> addCliente(@RequestBody ClienteDTO productDTO) {
        ClienteDTO createdCliente = service.addCliente(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO productDTO) {
        boolean updated = service.updateCliente(id, productDTO);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        service.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}