package com.erp.controle.financeiro.config;

import com.erp.controle.financeiro.dto.UserRegistrationDTO;
import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.entities.RoleModel;
import com.erp.controle.financeiro.entities.UserModel;
import com.erp.controle.financeiro.repositories.RoleRepository;
import com.erp.controle.financeiro.repositories.UserRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new User(userModel.getUsername(), userModel.getPassword(), true, true, true, true, userModel.getAuthorities());
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findById(Long id) {
        Optional<UserModel> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void registerUser(UserRegistrationDTO userDTO) {
        UserModel user = new UserModel();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Lembre-se de usar um encoder para a senha

        RoleModel role = roleRepository.findByRoleName(userDTO.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);
    }

//    @Transactional
//    public void updateUser(Long userId, UserRegistrationDTO userDTO) {
//        UserModel existingUser = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        existingUser.setUsername(userDTO.getUsername());
//        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//
//        RoleModel role = roleRepository.findByRoleName(userDTO.getRoleName())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//
//        existingUser.setRoles(Collections.singletonList(role));
//
//        userRepository.save(existingUser);
//    }
}