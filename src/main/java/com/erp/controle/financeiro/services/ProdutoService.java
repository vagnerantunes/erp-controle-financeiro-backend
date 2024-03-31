package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private FornecedorService fornecedorService;

	public List<Produto> getAll() {
		return repository.findAll();
	}

	public Page<Produto> getAllPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}

	public Produto insert(Produto obj) {
		obj.setProId(null);
		obj.setFornecedor(fornecedorService.findById(obj.getFornecedor().getForId()));
		obj = repository.save(obj);
		return obj;
	}

	@SuppressWarnings("deprecation")
	public Produto update(Long id, Produto obj) {
		Produto entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Produto entity, Produto obj) {
		entity.setFornecedor(obj.getFornecedor());
		entity.setProDescricao(obj.getProDescricao());
		entity.setProPrecoCusto(obj.getProPrecoCusto());
		entity.setProPrecoVenda(obj.getProPrecoVenda());
		entity.setProEstoque(obj.getProEstoque());
		entity.setProFlag(obj.getProFlag());
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}