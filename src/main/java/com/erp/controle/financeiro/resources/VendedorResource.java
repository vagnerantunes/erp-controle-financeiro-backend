package com.erp.controle.financeiro.resources;

import com.erp.controle.financeiro.entities.Vendedor;
import com.erp.controle.financeiro.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/vendedores")
public class VendedorResource {

    @Autowired
    private VendedorService service;

    @GetMapping
    public ResponseEntity<List<Vendedor>> getAll() {
        List<Vendedor> vendedor = service.getAll();
        return ResponseEntity.ok(vendedor);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Vendedor>> getAllPage(Pageable pageable) {
        Page<Vendedor> page = service.getAllPage(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> findById(@PathVariable Long id) {
        Optional<Vendedor> vendedor = service.findById(id);
        return vendedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vendedor> insert(@RequestBody Vendedor vendedor) {
        Vendedor created = service.insert(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        if (service.update(id, vendedor)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}