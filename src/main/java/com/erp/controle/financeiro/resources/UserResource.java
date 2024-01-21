package com.erp.controle.financeiro.resources;

import com.erp.controle.financeiro.config.UserDetailsServiceImpl;
import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.entities.UserModel;
import com.erp.controle.financeiro.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = "/usuarios")
public class UserResource {

    @Autowired
    private UserDetailsServiceImpl service;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable Long id) {
        UserModel obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<UserModel> insert(@RequestBody UserModel user) {
        UserModel newUser = service.insert(user);
        // Retorna a URI do novo recurso criado
        return ResponseEntity.created(URI.create("/usuarios/" + newUser.getId())).body(newUser);
    }


}
