package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import com.erp.controle.financeiro.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.erp.controle.financeiro.entities.Cliente;
import com.erp.controle.financeiro.repositories.ClienteRepository;
import com.erp.controle.financeiro.services.exceptions.DatabaseException;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	public List<ClienteDTO> getAllClientes() {
		List<Cliente> clientes = repository.findAll();
		return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Page<ClienteDTO> getAllClientesPage(Pageable pageable) {
		Page<Cliente> clientePage = repository.findAll(pageable);
		return clientePage.map(this::convertToDTO);
	}

	public Optional<ClienteDTO> getClienteById(Long id) {
		return repository.findById(id).map(this::convertToDTO);
	}

	public ClienteDTO addCliente(ClienteDTO clienteDTO) {
		Cliente cliente = convertToEntity(clienteDTO);
		Cliente savedCliente = repository.save(cliente);
		return convertToDTO(savedCliente);
	}

	public boolean updateCliente(Long id, ClienteDTO clienteDTO) {
		Optional<Cliente> optionalCliente = repository.findById(id);
		if (optionalCliente.isPresent()) {
			Cliente cliente = optionalCliente.get();
			cliente.setCliNome(clienteDTO.getCliNome());
			cliente.setCliTipo(clienteDTO.getCliCpfCnpj());
			cliente.setCliDataNascimento(clienteDTO.getCliDataNascimento());
			cliente.setCliContato(clienteDTO.getCliContato());
			cliente.setCliEmail(clienteDTO.getCliEmail());
			cliente.setCliRazaoSocial(clienteDTO.getCliRazaoSocial());
			cliente.setCliSegmento(clienteDTO.getCliSegmento());
			cliente.setCliFlag(clienteDTO.getCliFlag());
			repository.save(cliente);
			return true;
		}
		return false;
	}

	public void deleteCliente(Long id) {
		repository.deleteById(id);
	}

	private ClienteDTO convertToDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCliId(cliente.getCliId());
		clienteDTO.setCliNome(cliente.getCliNome());
		clienteDTO.setCliTipo(cliente.getCliTipo());
		clienteDTO.setCliCpfCnpj(cliente.getCliCpfCnpj());
		clienteDTO.setCliDataNascimento(cliente.getCliDataNascimento());
		clienteDTO.setCliContato(cliente.getCliContato());
		clienteDTO.setCliEmail(cliente.getCliEmail());
		clienteDTO.setCliRazaoSocial(cliente.getCliRazaoSocial());
		clienteDTO.setCliSegmento(cliente.getCliSegmento());
		clienteDTO.setCliFlag(cliente.getCliFlag());
		return clienteDTO;
	}

	private Cliente convertToEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setCliId(clienteDTO.getCliId());
		cliente.setCliNome(clienteDTO.getCliNome());
		cliente.setCliTipo(clienteDTO.getCliTipo());
		cliente.setCliCpfCnpj(clienteDTO.getCliCpfCnpj());
		cliente.setCliDataNascimento(clienteDTO.getCliDataNascimento());
		cliente.setCliContato(clienteDTO.getCliContato());
		cliente.setCliEmail(clienteDTO.getCliEmail());
		cliente.setCliRazaoSocial(clienteDTO.getCliRazaoSocial());
		cliente.setCliSegmento(clienteDTO.getCliSegmento());
		cliente.setCliFlag(clienteDTO.getCliFlag());
		return cliente;
	}


}