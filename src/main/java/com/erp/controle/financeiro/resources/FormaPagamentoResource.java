package com.erp.controle.financeiro.resources;

import java.util.List;
import java.util.Optional;

import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = "/fpagamentos")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> getAll() {
        List<FormaPagamento> formaPagamentos = formaPagamentoService.getAll();
        return ResponseEntity.ok(formaPagamentos);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FormaPagamento>> getAllPage(Pageable pageable) {
        Page<FormaPagamento> formaPagamentoPage = formaPagamentoService.getAllPage(pageable);
        return ResponseEntity.ok(formaPagamentoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> findById(@PathVariable Long id) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoService.findById(id);
        return formaPagamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> insert(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento createdFormaPagamento = formaPagamentoService.insert(formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFormaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        if (formaPagamentoService.update(id, formaPagamento)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        formaPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}