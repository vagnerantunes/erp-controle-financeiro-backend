package com.erp.controle.financeiro.resources;

import java.net.URI;
import java.util.List;

import com.erp.controle.financeiro.dto.FormaPagamentoDTO;
import com.erp.controle.financeiro.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.services.FormaPagamentoService;

@RestController
@RequestMapping(value = "/fpagamentos")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService service;

    @Autowired
    public FormaPagamentoResource(FormaPagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> getAllFormaPagamentos() {
        List<FormaPagamentoDTO> products = service.getAllFormaPagamentos();
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<FormaPagamentoDTO>> getAllFormaPagamentosPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                                             @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "fpgId") String sortBy,
                                                                             @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        Page<FormaPagamentoDTO> products = service.getAllFormaPagamentosPage(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoDTO> getFormaPagamentoById(@PathVariable Long id) {
        return service.getFormaPagamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> addFormaPagamento(@RequestBody FormaPagamentoDTO productDTO) {
        FormaPagamentoDTO createdFormaPagamento = service.addFormaPagamento(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFormaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFormaPagamento(@PathVariable Long id, @RequestBody FormaPagamentoDTO productDTO) {
        boolean updated = service.updateFormaPagamento(id, productDTO);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFormaPagamento(@PathVariable Long id) {
		service.deleteFormaPagamento(id);
		return ResponseEntity.noContent().build();
	}

}
