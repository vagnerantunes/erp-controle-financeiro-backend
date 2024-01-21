package com.erp.controle.financeiro.config;

import com.erp.controle.financeiro.entities.Produto;
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
    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel findById(Long id) {
        Optional<UserModel> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public UserModel insert(UserModel user) {
        // Criptografa a senha antes de salvar no banco de dados
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Adicione lógica adicional aqui, se necessário
        // ...

        return userRepository.save(user);
    }

}