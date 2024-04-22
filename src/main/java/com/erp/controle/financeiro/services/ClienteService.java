package com.erp.controle.financeiro.services;

import com.erp.controle.financeiro.dto.ClienteNewDTO;
import com.erp.controle.financeiro.dto.FornecedorNewDTO;
import com.erp.controle.financeiro.entities.*;
import com.erp.controle.financeiro.repositories.ClienteRepository;
import com.erp.controle.financeiro.repositories.EnderecoClienterRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoClienterRepository enderecoClienterRepository;
    private Pageable pageable;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> getAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cliente insert(Cliente obj) {
        try {
            obj.setCliId(null);
            obj = repository.save(obj);
            enderecoClienterRepository.saveAll(obj.getEnderecoClientes());
            return obj;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }

    }

    public Cliente update(Long id, ClienteNewDTO objDto) {
        try {
            Cliente entity = findById(id);

            // Atualiza os dados do fornecedor
            entity.setCliNome(objDto.getCliNome());
            entity.setCliTipo(objDto.getCliTipo());
            entity.setCliCpf(objDto.getCliCpf());
            entity.setCliCnpj(objDto.getCliCnpj());
            entity.setCliDataNascimento(objDto.getCliDataNascimento());
            entity.setCliFlag(objDto.getCliFlag());

            // Atualiza o endereço do fornecedor
            EnderecoCliente endereco = entity.getEnderecoClientes().get(0); // Assumindo que há apenas um endereço por cliente
            endereco.setEncRua(objDto.getEncRua());
            endereco.setEncNumero(objDto.getEncNumero());
            endereco.setEncBairro(objDto.getEncBairro());
            endereco.setEncCidade(objDto.getEncCidade());
            endereco.setEncCep(objDto.getEncCep());
            endereco.setEncEstado(objDto.getEncEstado());
            endereco.setEncComplemento(objDto.getEncComplemento());

            // Atualiza o contato
            ContatoCliente contato = entity.getContatoClientes().get(0); // Assumindo que há apenas um contato por cliente
            contato.setCclCelular(objDto.getCclCelular());
            contato.setCclTelefoneComercial(objDto.getCclTelefoneComercial());
            contato.setCclEmail(objDto.getCclEmail());
            contato.setCclEmailSecundario(objDto.getCclEmailSecundario());

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

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cliente = new Cliente(null, objDto.getCliNome(), objDto.getCliTipo(),
                objDto.getCliCpf(), objDto.getCliCpf(), objDto.getCliDataNascimento());

        EnderecoCliente enderecoCliente = new EnderecoCliente(null, cliente, objDto.getEncRua(), objDto.getEncNumero(),
                objDto.getEncBairro(), objDto.getEncCidade(), objDto.getEncCep(), objDto.getEncEstado(), objDto.getEncComplemento());

        ContatoCliente contatoCliente = new ContatoCliente(null, cliente, objDto.getCclCelular(),
                objDto.getCclTelefoneComercial(), objDto.getCclEmail(), objDto.getCclEmailSecundario());

        cliente.getEnderecoClientes().add(enderecoCliente);
        cliente.getContatoClientes().add(contatoCliente);

        return cliente;
    }

    public ClienteNewDTO toNewDTO(Cliente obj) {
        ClienteNewDTO dto = new ClienteNewDTO();

        // Mapeie os atributos comuns entre Fornecedor e FornecedorNewDTO
        dto.setCliId(obj.getCliId());
        dto.setCliNome(obj.getCliNome());
        dto.setCliTipo(obj.getCliTipo());
        dto.setCliCpf(obj.getCliCpf());
        dto.setCliCnpj(obj.getCliCnpj());
        dto.setCliDataNascimento(obj.getCliDataNascimento());
        dto.setCliFlag(obj.getCliFlag());

        // Atributos específicos de EnderecoFornecedor
        EnderecoCliente endereco = obj.getEnderecoClientes().get(0);
        dto.setEncRua(endereco.getEncRua());
        dto.setEncNumero(endereco.getEncNumero());
        dto.setEncBairro(endereco.getEncBairro());
        dto.setEncCidade(endereco.getEncCidade());
        dto.setEncCep(endereco.getEncCep());
        dto.setEncEstado(endereco.getEncEstado());
        dto.setEncComplemento(endereco.getEncComplemento());

        // Atributos específicos de Contato
        ContatoCliente contatoFornecedor = obj.getContatoClientes().get(0);
        dto.setCclCelular(contatoFornecedor.getCclCelular());
        dto.setCclTelefoneComercial(contatoFornecedor.getCclTelefoneComercial());
        dto.setCclEmail(contatoFornecedor.getCclEmail());
        dto.setCclEmailSecundario(contatoFornecedor.getCclEmailSecundario());

        return dto;
    }

}
