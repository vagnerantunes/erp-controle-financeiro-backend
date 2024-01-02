package com.erp.controle.financeiro.services;

import com.erp.controle.financeiro.dto.FornecedorDTO;
import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.entities.Contato;
import com.erp.controle.financeiro.entities.EnderecoFornecedor;
import com.erp.controle.financeiro.entities.Fornecedor;
import com.erp.controle.financeiro.repositories.EnderecoFornecedorRepository;
import com.erp.controle.financeiro.repositories.FornecedorRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;
import com.erp.controle.financeiro.services.exceptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;
    @Autowired
    private EnderecoFornecedorRepository enderecoFornecedorRepository;
    private Pageable pageable;

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Page<Fornecedor> getAllFornecedorsPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Fornecedor insert(Fornecedor obj) {
        try {
            obj.setForId(null);
            obj = repository.save(obj);
            enderecoFornecedorRepository.saveAll(obj.getEnderecos());
            return obj;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }

    }

    public Fornecedor update(Long id, FornecedorNewDTO objDto) {
        try {
            Fornecedor entity = findById(id);

            // Atualiza os dados do fornecedor
            entity.setForRazaoSocial(objDto.getForRazaoSocial());
            entity.setForNomeFantasia(objDto.getForNomeFantasia());
            entity.setForCnpj(objDto.getForCnpj());
            entity.setForFlag(objDto.getForFlag());

            // Atualiza o endereço do fornecedor
            EnderecoFornecedor endereco = entity.getEnderecos().get(0); // Assumindo que há apenas um endereço por cliente
            endereco.setEnfRua(objDto.getEnfRua());
            endereco.setEnfNumero(objDto.getEnfNumero());
            endereco.setEnfBairro(objDto.getEnfBairro());
            endereco.setEnfCidade(objDto.getEnfCidade());
            endereco.setEnfCep(objDto.getEnfCep());
            endereco.setEnfEstado(objDto.getEnfEstado());
            endereco.setEnfComplemento(objDto.getEnfComplemento());

            // Atualiza o contato
            Contato contato = entity.getContatos().get(0); // Assumindo que há apenas um contato por cliente
            contato.setConTelefoneComercial(objDto.getConTelefoneComercial());
            contato.setConCelular(objDto.getConCelular());
            contato.setConEmail(objDto.getConEmail());
            contato.setConEmailSecundario(objDto.getConEmailSecundario());

            // Salva as alterações
            repository.save(entity);

            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }

    }
    public void deleteFornecedor(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Fornecedor fromDTO(FornecedorNewDTO objDto) {
        Fornecedor fornec = new Fornecedor(null, objDto.getForRazaoSocial(), objDto.getForNomeFantasia(),
                objDto.getForCnpj());

        EnderecoFornecedor ender = new EnderecoFornecedor(null, fornec, objDto.getEnfRua(), objDto.getEnfNumero(),
                objDto.getEnfBairro(), objDto.getEnfCidade(), objDto.getEnfCep(),
                objDto.getEnfEstado(), objDto.getEnfComplemento());

        Contato contato = new Contato(null, fornec, objDto.getConTelefoneComercial(), objDto.getConCelular(),
                objDto.getConEmail(), objDto.getConEmailSecundario());

        fornec.getEnderecos().add(ender);
        fornec.getContatos().add(contato);

        return fornec;
    }
}