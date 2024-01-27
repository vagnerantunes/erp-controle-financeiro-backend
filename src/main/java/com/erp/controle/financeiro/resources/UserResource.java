package com.erp.controle.financeiro.resources;

import com.erp.controle.financeiro.config.UserDetailsServiceImpl;
import com.erp.controle.financeiro.dto.UserRegistrationDTO;
import com.erp.controle.financeiro.entities.UserModel;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        try {
            service.registerUser(userDTO);
            return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRegistrationDTO userDTO) {
        try {
            service.updateUser(id, userDTO);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
