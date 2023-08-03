package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.FornecedorDTO;
import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.FornecedorRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class FornecedorService {
	@Autowired
	private FornecedorRepository repository;

	public List<FornecedorDTO> getAllFornecedors() {
		List<Fornecedor> fornecedors = repository.findAll();
		return fornecedors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Page<FornecedorDTO> getAllFornecedorsPage(Pageable pageable) {
		Page<Fornecedor> fornecedorPage = repository.findAll(pageable);
		return fornecedorPage.map(this::convertToDTO);
	}

	public Optional<FornecedorDTO> getFornecedorById(Long id) {
		return repository.findById(id).map(this::convertToDTO);
	}

	public FornecedorDTO addFornecedor(FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = convertToEntity(fornecedorDTO);
		Fornecedor savedFornecedor = repository.save(fornecedor);
		return convertToDTO(savedFornecedor);
	}

	public boolean updateFornecedor(Long id, FornecedorDTO fornecedorDTO) {
		Optional<Fornecedor> optionalFornecedor = repository.findById(id);
		if (optionalFornecedor.isPresent()) {
			Fornecedor fornecedor = optionalFornecedor.get();
			fornecedor.setForNome(fornecedorDTO.getForNome());
			fornecedor.setForTipo(fornecedorDTO.getForTipo());
			fornecedor.setForCpfCnpj(fornecedorDTO.getForCpfCnpj());
			fornecedor.setForFantasia(fornecedorDTO.getForFantasia());
			fornecedor.setForEndereco(fornecedorDTO.getForEndereco());
			fornecedor.setForContato(fornecedorDTO.getForContato());
			fornecedor.setForEmail(fornecedorDTO.getForEmail());
			fornecedor.setForFlag(fornecedorDTO.getForFlag());
			repository.save(fornecedor);
			return true;
		}
		return false;
	}

	public void deleteFornecedor(Long id) {
		repository.deleteById(id);
	}

	private FornecedorDTO convertToDTO(Fornecedor fornecedor) {
		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		fornecedorDTO.setForId(fornecedor.getForId());
		fornecedorDTO.setForNome(fornecedor.getForNome());
		fornecedorDTO.setForTipo(fornecedor.getForTipo());
		fornecedorDTO.setForCpfCnpj(fornecedor.getForCpfCnpj());
		fornecedorDTO.setForFantasia(fornecedor.getForFantasia());
		fornecedorDTO.setForEndereco(fornecedor.getForEndereco());
		fornecedorDTO.setForContato(fornecedor.getForContato());
		fornecedorDTO.setForEmail(fornecedor.getForEmail());
		fornecedorDTO.setForFlag(fornecedor.getForFlag());
		return fornecedorDTO;
	}

	private Fornecedor convertToEntity(FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setForId(fornecedorDTO.getForId());
		fornecedor.setForNome(fornecedorDTO.getForNome());
		fornecedor.setForTipo(fornecedorDTO.getForTipo());
		fornecedor.setForCpfCnpj(fornecedorDTO.getForCpfCnpj());
		fornecedor.setForFantasia(fornecedorDTO.getForFantasia());
		fornecedor.setForEndereco(fornecedorDTO.getForEndereco());
		fornecedor.setForContato(fornecedorDTO.getForContato());
		fornecedor.setForEmail(fornecedorDTO.getForEmail());
		fornecedor.setForFlag(fornecedorDTO.getForFlag());
		return fornecedor;
	}


}