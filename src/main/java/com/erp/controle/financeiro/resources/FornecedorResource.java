package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.entities.Fornecedor;

//Foi incluido na classe securitConfig o metodo para liberar acesso cors
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource {

    @Autowired
    private FornecedorService service;

//    @Autowired
//    public FornecedorResource(FornecedorService service) {
//        this.service = service;
//    }
    @GetMapping
    public ResponseEntity<List<FornecedorNewDTO>> findAll() {
        List<Fornecedor> list = service.findAll();
        List<FornecedorNewDTO> listDto = list.stream().map(obj -> service.toNewDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Fornecedor>> getAllFornecedorsPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                                  @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "forId") String sortBy,
                                                                  @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        Page<Fornecedor> fornecedorPage = service.getAllFornecedorsPage(pageable);
        return ResponseEntity.ok(fornecedorPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorNewDTO> findById(@PathVariable Long id) {
        Fornecedor obj = service.findById(id);
        FornecedorNewDTO dto = service.toDTO(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody FornecedorNewDTO objDto) {
        Fornecedor obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getForId()).toUri();
        return ResponseEntity.created(uri).build();
    }

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