package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {
	@Autowired
	private FormaPagamentoRepository repository;

	public List<FormaPagamento> getAll() {
		return repository.findAll();
	}

	public Page<FormaPagamento> getAllPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<FormaPagamento> findById(Long id) {
		return repository.findById(id);
	}

	public FormaPagamento insert(FormaPagamento formaPagamento) {
		return repository.save(formaPagamento);
	}

	public boolean update(Long id, FormaPagamento formaPagamento) {
		Optional<FormaPagamento> optionalFormaPagamento = repository.findById(id);
		if (optionalFormaPagamento.isPresent()) {
			FormaPagamento fpagamento = optionalFormaPagamento.get();
			fpagamento.setFpgDescricao(formaPagamento.getFpgDescricao());
			fpagamento.setFpgTipo(formaPagamento.getFpgTipo());
			fpagamento.setFpgQtdParcela(formaPagamento.getFpgQtdParcela());
			fpagamento.setFpgAprovacao(formaPagamento.getFpgAprovacao());
			fpagamento.setFpgTipoAcresDesc(formaPagamento.getFpgTipoAcresDesc());
			fpagamento.setFpgDescricaoAcreDesc(formaPagamento.getFpgDescricaoAcreDesc());
			fpagamento.setFpgValor(formaPagamento.getFpgValor());
			fpagamento.setFpgPorcentagem(formaPagamento.getFpgPorcentagem());
			fpagamento.setFpgFlag(formaPagamento.getFpgFlag());
			repository.save(fpagamento);
			return true;
		}
		return false;
	}
	public void delete(Long id) {
		repository.deleteById(id);
	}
}