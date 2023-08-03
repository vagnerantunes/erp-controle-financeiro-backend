package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.BoletoDTO;
import com.erp.controle.financeiro.entities.Boleto;
import com.erp.controle.financeiro.repositories.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Boleto;
import com.erp.controle.financeiro.repositories.BoletoRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class BoletoService {

	@Autowired
	private BoletoRepository repository;

	public List<BoletoDTO> getAllBoletos() {
		List<Boleto> boletos = repository.findAll();
		return boletos.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Page<BoletoDTO> getAllBoletosPage(Pageable pageable) {
		Page<Boleto> boletoPage = repository.findAll(pageable);
		return boletoPage.map(this::convertToDTO);
	}

	public Optional<BoletoDTO> getBoletoById(Long id) {
		return repository.findById(id).map(this::convertToDTO);
	}

	public BoletoDTO addBoleto(BoletoDTO boletoDTO) {
		Boleto boleto = convertToEntity(boletoDTO);
		Boleto savedBoleto = repository.save(boleto);
		return convertToDTO(savedBoleto);
	}

	public boolean updateBoleto(Long id, BoletoDTO boletoDTO) {
		Optional<Boleto> optionalBoleto = repository.findById(id);
		if (optionalBoleto.isPresent()) {
			Boleto boleto = optionalBoleto.get();
			boleto.setBolDescricao(boletoDTO.getBolDescricao());
			boleto.setBolData(boletoDTO.getBolData());
			boleto.setBolValor(boletoDTO.getBolValor());
			boleto.setBolQtdParcela(boletoDTO.getBolQtdParcela());
			boleto.setBolContaPfPj(boletoDTO.getBolContaPfPj());
			boleto.setBolObs(boletoDTO.getBolObs());
			repository.save(boleto);
			return true;
		}
		return false;
	}

	public void deleteBoleto(Long id) {
		repository.deleteById(id);
	}

	private BoletoDTO convertToDTO(Boleto boleto) {
		BoletoDTO boletoDTO = new BoletoDTO();
		boletoDTO.setBolId(boleto.getBolId());
		boletoDTO.setBolDescricao(boleto.getBolDescricao());
		boletoDTO.setBolData(boleto.getBolData());
		boletoDTO.setBolValor(boleto.getBolValor());
		boletoDTO.setBolQtdParcela(boleto.getBolQtdParcela());
		boletoDTO.setBolContaPfPj(boleto.getBolContaPfPj());
		boletoDTO.setBolObs(boleto.getBolObs());
		return boletoDTO;
	}

	private Boleto convertToEntity(BoletoDTO boletoDTO) {
		Boleto boleto = new Boleto();
		boleto.setBolId(boletoDTO.getBolId());
		boleto.setBolDescricao(boletoDTO.getBolDescricao());
		boleto.setBolData(boletoDTO.getBolData());
		boleto.setBolValor(boletoDTO.getBolValor());
		boleto.setBolQtdParcela(boletoDTO.getBolQtdParcela());
		boleto.setBolContaPfPj(boletoDTO.getBolContaPfPj());
		boleto.setBolObs(boletoDTO.getBolObs());
		return boleto;
	}
}