package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.erp.controle.financeiro.dto.CompraDTO;
import com.erp.controle.financeiro.entities.Compra;
import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Compra;
import com.erp.controle.financeiro.repositories.CompraRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;
	@Autowired
	private FornecedorService  fornecedorService;
	public List<CompraDTO> getAllCompras() {
		List<Compra> compras = repository.findAll();
		return compras.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Page<CompraDTO> getAllComprasPage(Pageable pageable) {
		Page<Compra> compraPage = repository.findAll(pageable);
		return compraPage.map(this::convertToDTO);
	}

	public Optional<CompraDTO> getCompraById(Long id) {
		return repository.findById(id).map(this::convertToDTO);
	}

	public CompraDTO addCompra(CompraDTO compraDTO) {
		Compra compra = convertToEntity(compraDTO);

		Long fornecedorId = compraDTO.getComForId(); // Obtém o ID do fornecedor da DTO
		Fornecedor fornecedor = fornecedorService.findById(fornecedorId); // Obtém o fornecedor pelo ID

		compra.setComForId(fornecedor); // Define o fornecedor na compra
		Compra savedCompra = repository.save(compra);
		return convertToDTO(savedCompra);
	}

	public boolean updateCompra(Long id, CompraDTO compraDTO) {
		Optional<Compra> optionalCompra = repository.findById(id);
		if (optionalCompra.isPresent()) {
			Compra compra = optionalCompra.get();

			Long fornecedorId = compraDTO.getComForId(); // Obtém o ID do fornecedor da DTO
			Fornecedor fornecedor = fornecedorService.findById(fornecedorId); // Obtém o fornecedor pelo ID

			compra.setComForId(fornecedor); // Define o fornecedor na compra

			// Atualiza os demais atributos da compra
			compra.setComData(compraDTO.getComData());
			compra.setComValorTotal(compraDTO.getComValorTotal());
			compra.setComDesconto(compraDTO.getComDesconto());
			compra.setComJuros(compraDTO.getComJuros());
			compra.setComStsDoc(compraDTO.getComStsDoc());
			repository.save(compra);
			return true;
		}
		return false;
	}
	public void deleteCompra(Long id) {
		repository.deleteById(id);
	}

	private CompraDTO convertToDTO(Compra compra) {
		CompraDTO compraDTO = new CompraDTO();
		compraDTO.setComId(compra.getComId());
		compraDTO.setComForId(compra.getComForId().getForId()); // Define o ID do fornecedor na DTO
		compraDTO.setComData(compra.getComData());
		compraDTO.setComValorTotal(compra.getComValorTotal());
		compraDTO.setComDesconto(compra.getComDesconto());
		compraDTO.setComJuros(compra.getComJuros());
		compraDTO.setComStsDoc(compra.getComStsDoc());
		return compraDTO;
	}

	private Compra convertToEntity(CompraDTO compraDTO) {
		Compra compra = new Compra();
		compra.setComId(compraDTO.getComId());

		Long fornecedorId = compraDTO.getComForId(); // Obtém o ID do fornecedor da DTO
		Fornecedor fornecedor = fornecedorService.findById(fornecedorId); // Obtém o fornecedor pelo ID

		compra.setComForId(fornecedor); // Define o fornecedor na compra

		compra.setComData(compraDTO.getComData());
		compra.setComValorTotal(compraDTO.getComValorTotal());
		compra.setComDesconto(compraDTO.getComDesconto());
		compra.setComJuros(compraDTO.getComJuros());
		compra.setComStsDoc(compraDTO.getComStsDoc());
		return compra;
	}


}