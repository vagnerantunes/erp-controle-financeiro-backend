package com.erp.controle.financeiro.resources;

import com.erp.controle.financeiro.config.UserDetailsServiceImpl;
import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.entities.UserModel;
import com.erp.controle.financeiro.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = "/usuarios")
public class UserResource {

    @Autowired
    private UserDetailsServiceImpl service;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        List<UserModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
