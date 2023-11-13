package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.FornecedorDTO;
import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.entities.EnderecoFornecedor;
import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.EnderecoFornecedorRepository;
import com.erp.controle.financeiro.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.FornecedorRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;

@Service
public class FornecedorService {
	@Autowired
	private FornecedorRepository repository;

	@Autowired
	private EnderecoFornecedorRepository enderecoFornecedorRepository;
	public List<FornecedorDTO> getAllFornecedors() {
		List<Fornecedor> fornecedors = repository.findAll();
		return fornecedors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	public Page<FornecedorDTO> getAllFornecedorsPage(Pageable pageable) {
		Page<Fornecedor> fornecedorPage = repository.findAll(pageable);
		return fornecedorPage.map(this::convertToDTO);
	}
	public Fornecedor findById(Long id) {
		Optional<Fornecedor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Fornecedor insert(Fornecedor obj) {
		obj.setForId(null);
		obj = repository.save(obj);
		enderecoFornecedorRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Fornecedor update(Long id, FornecedorNewDTO objDto) {
		try {
			Fornecedor entity = findById(id);

			// Atualiza os dados do cliente
			entity.setForRazaoSocial(objDto.getForRazaoSocial());
			entity.setForFantasia(objDto.getForFantasia());
			entity.setForCnpj(objDto.getForCnpj());
			entity.setForFlag(objDto.getForFlag());
			entity.setForObs(objDto.getForObs());

			// Atualiza o endereço do cliente
			EnderecoFornecedor endereco = entity.getEnderecos().get(0); // Assumindo que há apenas um endereço por cliente
			endereco.setEnfBairro(objDto.getEnfBairro());
			endereco.setEnfCidade(objDto.getEnfCidade());
			endereco.setEnfEstado(objDto.getEnfEstado());
			endereco.setEnfCep(objDto.getEnfCep());
			endereco.setEnfPais(objDto.getEnfPais());
			endereco.setEnfObs(objDto.getEnfObs());

			// Salva as alterações
			repository.save(entity);

			return entity;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

//	public Fornecedor update(Long id, Fornecedor obj) {
//		try {
//			Fornecedor entity = findById(obj.getForId());
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//
//	private void updateData(Fornecedor entity, Fornecedor obj) {
//		entity.setForRazaoSocial(obj.getForRazaoSocial());
//		entity.setForFantasia(obj.getForFantasia());
//		entity.setForCnpj(obj.getForCnpj());
//		entity.setForFlag(obj.getForFlag());
//		entity.setForObs(obj.getForObs());
//	}
	public void deleteFornecedor(Long id) {
		repository.deleteById(id);
	}
	private FornecedorDTO convertToDTO(Fornecedor fornecedor) {
		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		fornecedorDTO.setForId(fornecedor.getForId());
		fornecedorDTO.setForRazaoSocial(fornecedor.getForRazaoSocial());
		fornecedorDTO.setForFantasia(fornecedor.getForFantasia());
		fornecedorDTO.setForCnpj(fornecedor.getForCnpj());
		fornecedorDTO.setForFlag(fornecedor.getForFlag());
		fornecedorDTO.setForObs(fornecedor.getForObs());
		return fornecedorDTO;
	}

	private Fornecedor convertToEntity(FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setForId(fornecedorDTO.getForId());
		fornecedor.setForRazaoSocial(fornecedorDTO.getForRazaoSocial());
		fornecedor.setForFantasia(fornecedorDTO.getForFantasia());
		fornecedor.setForCnpj(fornecedorDTO.getForCnpj());
		fornecedor.setForFlag(fornecedorDTO.getForFlag());
		fornecedor.setForObs(fornecedorDTO.getForObs());
		return fornecedor;
	}

	public Fornecedor fromDTO(FornecedorDTO objDto) {
		return new Fornecedor(objDto.getForId(), objDto.getForRazaoSocial(), objDto.getForFantasia(),
				objDto.getForCnpj(),objDto.getForFlag(),
				objDto.getForObs());
	}

	public Fornecedor fromDTO(FornecedorNewDTO objDto) {
		Fornecedor fornec = new Fornecedor(null, objDto.getForRazaoSocial(), objDto.getForFantasia(),
				objDto.getForCnpj(), objDto.getForFlag(), objDto.getForObs());

		EnderecoFornecedor ender = new EnderecoFornecedor(null, fornec, objDto.getEnfRua(), objDto.getEnfNumero(),
				objDto.getEnfBairro(), objDto.getEnfCidade(), objDto.getEnfEstado(), objDto.getEnfCep(),
				objDto.getEnfPais(), objDto.getEnfObs());

		fornec.getEnderecos().add(ender);

		return fornec;
	}
}