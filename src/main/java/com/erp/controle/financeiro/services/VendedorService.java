package com.erp.controle.financeiro.services;

import com.erp.controle.financeiro.entities.FormaPagamento;
import com.erp.controle.financeiro.entities.Vendedor;
import com.erp.controle.financeiro.repositories.FormaPagamentoRepository;
import com.erp.controle.financeiro.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repository;

    public List<Vendedor> getAll() {
        return repository.findAll();
    }

    public Page<Vendedor> getAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Vendedor> findById(Long id) {
        return repository.findById(id);
    }

    public Vendedor insert(Vendedor vendedor) {
        return repository.save(vendedor);
    }

    public boolean update(Long id, Vendedor vendedor) {
        Optional<Vendedor> optional = repository.findById(id);
        if (optional.isPresent()) {
            Vendedor vendedor1 = optional.get();
            vendedor1.setVndNome(vendedor.getVndNome());
            vendedor1.setVndCargo(vendedor.getVndCargo());
            vendedor1.setVndNivel(vendedor.getVndNivel());
            vendedor1.setVndMeta(vendedor.getVndMeta());
            vendedor1.setVndStatus(vendedor.getVndStatus());
            vendedor1.setVndObs(vendedor.getVndObs());
            repository.save(vendedor1);
            return true;
        }
        return false;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}