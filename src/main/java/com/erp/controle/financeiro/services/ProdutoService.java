package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.dto.ProdutoDTO;
import com.erp.controle.financeiro.entities.Contato;
import com.erp.controle.financeiro.entities.EnderecoFornecedor;
import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.services.exceptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.repositories.ProdutoRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Page<Produto> getAllFornecedorsPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));	
	}

	public Produto insert(Produto obj) {
		try {
			obj.setProId(null);
			obj = repository.save(obj);
			return obj;
		} catch (DataIntegrityViolationException e) {
			throw new ValueBigForAtributeException(e.getMessage());
		}
	}

	public Produto update(Long id, ProdutoDTO objDto) {
		try {
			Produto entity = findById(id);

			// Atualiza os dados do produto
			entity.setProDescricao(objDto.getProDescricao());
			entity.setProPrecoCusto(objDto.getProPrecoCusto());
			entity.setProPrecoVenda(objDto.getProPrecoVenda());
			entity.setProEstoque(objDto.getProEstoque());
			entity.setProEstoqueVendido(objDto.getProEstoqueVendido());
			entity.setProFlag(objDto.getProFlag());

			// Salva as alterações
			repository.save(entity);

			return entity;
		} catch (DataIntegrityViolationException e) {
			throw new ValueBigForAtributeException(e.getMessage());
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Produto fromDTO(ProdutoDTO objDto) {
		Produto produto = new Produto(null, objDto.getProDescricao(), objDto.getProPrecoCusto(),
				objDto.getProPrecoVenda(), objDto.getProEstoque(), objDto.getProEstoqueVendido());

		return produto;
	}

	public ProdutoDTO toNewDTO(Produto obj) {
		ProdutoDTO dto = new ProdutoDTO();

		// Mapeie os atributos comuns entre Fornecedor e FornecedorNewDTO
		dto.setProId(obj.getProId());
		dto.setProDescricao(obj.getProDescricao());
		dto.setProPrecoCusto(obj.getProPrecoCusto());
		dto.setProPrecoVenda(obj.getProPrecoVenda());
		dto.setProEstoque(obj.getProEstoque());
		dto.setProEstoqueVendido(obj.getProEstoqueVendido());
		dto.setProFlag(obj.getProFlag());

		return dto;
	}
}