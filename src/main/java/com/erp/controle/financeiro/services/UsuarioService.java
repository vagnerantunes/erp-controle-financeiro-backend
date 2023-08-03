package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.erp.controle.financeiro.dto.UsuarioDTO;
import com.erp.controle.financeiro.entities.Usuario;
import com.erp.controle.financeiro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Usuario;
import com.erp.controle.financeiro.repositories.UsuarioRepository;
import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<UsuarioDTO> getAllUsuarios() {
		List<Usuario> usuarios = repository.findAll();
		return usuarios.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Page<UsuarioDTO> getAllUsuariosPage(Pageable pageable) {
		Page<Usuario> usuarioPage = repository.findAll(pageable);
		return usuarioPage.map(this::convertToDTO);
	}

	public Optional<UsuarioDTO> getUsuarioById(Long id) {
		return repository.findById(id).map(this::convertToDTO);
	}

	public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = convertToEntity(usuarioDTO);
		Usuario savedUsuario = repository.save(usuario);
		return convertToDTO(savedUsuario);
	}

	public boolean updateUsuario(Long id, UsuarioDTO usuarioDTO) {
		Optional<Usuario> optionalUsuario = repository.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			usuario.setUsuNome(usuarioDTO.getUsuNome());
			usuario.setUsuFlag(usuarioDTO.getUsuFlag());
			usuario.setUsuFuncao(usuarioDTO.getUsuFuncao());
			usuario.setUsuSenha(usuarioDTO.getUsuSenha());
			repository.save(usuario);
			return true;
		}
		return false;
	}

	public void deleteUsuario(Long id) {
		repository.deleteById(id);
	}

	private UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuId(usuario.getUsuId());
		usuarioDTO.setUsuNome(usuario.getUsuNome());
		usuarioDTO.setUsuFlag(usuario.getUsuFlag());
		usuarioDTO.setUsuFuncao(usuario.getUsuFuncao());
		usuarioDTO.setUsuSenha(usuario.getUsuSenha());
		return usuarioDTO;
	}

	private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setUsuId(usuarioDTO.getUsuId());
		usuario.setUsuNome(usuarioDTO.getUsuNome());
		usuario.setUsuFlag(usuarioDTO.getUsuFlag());
		usuario.setUsuFuncao(usuarioDTO.getUsuFuncao());
		usuario.setUsuSenha(usuarioDTO.getUsuSenha());
		return usuario;
	}
	
}