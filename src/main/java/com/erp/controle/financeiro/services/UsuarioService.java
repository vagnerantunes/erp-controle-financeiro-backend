package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.dto.UsuarioDTO;
import com.erp.controle.financeiro.entities.*;
import com.erp.controle.financeiro.repositories.UsuarioRepository;
import com.erp.controle.financeiro.services.exceptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Usuario;
import com.erp.controle.financeiro.repositories.UsuarioRepository;
import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder pe;

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Page<Usuario> getAllUsuariosPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Usuario insert(Usuario obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new ValueBigForAtributeException(e.getMessage());
		}
	}

	public Usuario update(Long id, UsuarioDTO objDto) {
		try {
			Usuario entity = findById(id);

			// Atualiza os dados do fornecedor
			entity.setUsuNome(objDto.getUsuNome());
			entity.setUsuFlag(objDto.getUsuFlag());
			entity.setUsuFuncao(objDto.getUsuFuncao());
			entity.setUsuSenha(objDto.getUsuSenha());

			// Salva as alterações
			repository.save(entity);

			return entity;
		} catch (DataIntegrityViolationException e) {
			throw new ValueBigForAtributeException(e.getMessage());
		}

	}

	public void deleteUsuario(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		Usuario usuario = new Usuario(null, objDto.getUsuNome(), objDto.getUsuFuncao(),
				pe.encode(objDto.getUsuSenha()) );

		return usuario;
	}


	public UsuarioDTO toNewDTO(Usuario obj) {
		UsuarioDTO dto = new UsuarioDTO();

		// Mapeie os atributos comuns entre Fornecedor e FornecedorNewDTO
		dto.setUsuId(obj.getUsuId());
		dto.setUsuNome(obj.getUsuNome());
		dto.setUsuFlag(obj.getUsuFlag());
		dto.setUsuFuncao(obj.getUsuFuncao());
		//dto.setUsuSenha(obj.getUsuSenha());

		return dto;
	}
	
}