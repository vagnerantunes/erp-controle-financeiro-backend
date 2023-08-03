package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.FormaPagamentoDTO;
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

	public List<FormaPagamentoDTO> getAllFormaPagamentos() {
		List<FormaPagamento> fpagamentos = repository.findAll();
		return fpagamentos.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Page<FormaPagamentoDTO> getAllFormaPagamentosPage(Pageable pageable) {
		Page<FormaPagamento> fpagamentoPage = repository.findAll(pageable);
		return fpagamentoPage.map(this::convertToDTO);
	}

	public Optional<FormaPagamentoDTO> getFormaPagamentoById(Long id) {
		return repository.findById(id).map(this::convertToDTO);
	}

	public FormaPagamentoDTO addFormaPagamento(FormaPagamentoDTO fpagamentoDTO) {
		FormaPagamento fpagamento = convertToEntity(fpagamentoDTO);
		FormaPagamento savedFormaPagamento = repository.save(fpagamento);
		return convertToDTO(savedFormaPagamento);
	}

	public boolean updateFormaPagamento(Long id, FormaPagamentoDTO fpagamentoDTO) {
		Optional<FormaPagamento> optionalFormaPagamento = repository.findById(id);
		if (optionalFormaPagamento.isPresent()) {
			FormaPagamento fpagamento = optionalFormaPagamento.get();
			fpagamento.setFpgTipoConta(fpagamentoDTO.getFpgTipoConta());
			fpagamento.setFpgDescricao(fpagamentoDTO.getFpgDescricao());
			fpagamento.setFpgTipo(fpagamentoDTO.getFpgTipo());
			fpagamento.setFpgQtdParcela(fpagamentoDTO.getFpgQtdParcela());
			fpagamento.setFpgPorcentagem(fpagamentoDTO.getFpgPorcentagem());
			fpagamento.setFpgFlag(fpagamentoDTO.getFpgFlag());
			repository.save(fpagamento);
			return true;
		}
		return false;
	}

	public void deleteFormaPagamento(Long id) {
		repository.deleteById(id);
	}

	private FormaPagamentoDTO convertToDTO(FormaPagamento fpagamento) {
		FormaPagamentoDTO fpagamentoDTO = new FormaPagamentoDTO();
		fpagamentoDTO.setFpgId(fpagamento.getFpgId());
		fpagamentoDTO.setFpgTipoConta(fpagamento.getFpgTipoConta());
		fpagamentoDTO.setFpgDescricao(fpagamento.getFpgDescricao());
		fpagamentoDTO.setFpgTipo(fpagamento.getFpgTipo());
		fpagamentoDTO.setFpgQtdParcela(fpagamento.getFpgQtdParcela());
		fpagamentoDTO.setFpgPorcentagem(fpagamento.getFpgPorcentagem());
		fpagamentoDTO.setFpgFlag(fpagamento.getFpgFlag());
		return fpagamentoDTO;
	}

	private FormaPagamento convertToEntity(FormaPagamentoDTO fpagamentoDTO) {
		FormaPagamento fpagamento = new FormaPagamento();
		fpagamento.setFpgId(fpagamentoDTO.getFpgId());
		fpagamento.setFpgTipoConta(fpagamentoDTO.getFpgTipoConta());
		fpagamento.setFpgDescricao(fpagamentoDTO.getFpgDescricao());
		fpagamento.setFpgTipo(fpagamentoDTO.getFpgTipo());
		fpagamento.setFpgQtdParcela(fpagamentoDTO.getFpgQtdParcela());
		fpagamento.setFpgPorcentagem(fpagamentoDTO.getFpgPorcentagem());
		fpagamento.setFpgFlag(fpagamentoDTO.getFpgFlag());
		return fpagamento;
	}
	
}