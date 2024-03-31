package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;

import com.erp.controle.financeiro.config.UserDetailsServiceImpl;
import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.controle.financeiro.entities.Venda;
import com.erp.controle.financeiro.repositories.VendaRepository;
import com.erp.controle.financeiro.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public List<Venda> getAll() {
        return repository.findAll();
    }

    public Page<Venda> getAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Venda> findById(Long id) {
        return repository.findById(id);
    }

    public Venda insert(Venda obj) {
        obj.setVenId(null);
        obj.setCliente(clienteService.findById(obj.getCliente().getCliId()));
        obj = repository.save(obj);
        return obj;
    }

    @SuppressWarnings("deprecation")
    public Venda update(Long id, Venda obj) {
        Venda entity = repository.getOne(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Venda entity, Venda obj) {
        entity.setCliente(obj.getCliente());
        entity.setFpagamento(obj.getFpagamento());
        entity.setVendedor(obj.getVendedor());
        entity.setVenData(obj.getVenData());
        entity.setVenValorTotal(obj.getVenValorTotal());
        entity.setVenDesconto(obj.getVenDesconto());
        entity.setVenJuros(obj.getVenJuros());
        entity.setVenStatus(obj.getVenStatus());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}