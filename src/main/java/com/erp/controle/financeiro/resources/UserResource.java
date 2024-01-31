package com.erp.controle.financeiro.resources;

import com.erp.controle.financeiro.config.UserDetailsServiceImpl;
import com.erp.controle.financeiro.dto.UserDto;
import com.erp.controle.financeiro.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = "/usuarios")
public class UserResource {

    @Autowired
    private UserDetailsServiceImpl service;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = service.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        Optional<UserModel> user = service.getUserById(userId);
        return user.map(value -> new ResponseEntity<>(convertToDto(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserModel user) {
        UserModel createdUser = service.createUser(user);
        return new ResponseEntity<>(convertToDto(createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserModel user) {
        user.setId(userId);
        UserModel updatedUser = service.updateUser(user);
        return new ResponseEntity<>(convertToDto(updatedUser), HttpStatus.OK);
    }

    // Helper method to convert UserModel to UserDto
    private UserDto convertToDto(UserModel user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
