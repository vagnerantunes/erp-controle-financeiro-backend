package com.erp.controle.financeiro.services;

import java.util.List;
import java.util.Optional;
import com.erp.controle.financeiro.entities.ItemVenda;
import com.erp.controle.financeiro.repositories.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.erp.controle.financeiro.entities.Venda;
import com.erp.controle.financeiro.repositories.VendaRepository;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ProdutoService produtoService;

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

        for (ItemVenda iv : obj.getItens()){
            // no findById de produto é preciso ter o ObjectNotFoundException
            iv.setProduto(produtoService.findById(iv.getProduto().getProId()));
            /*iv.setItvQtd(0.0); para gravar um valor que o usuário digitar, não coloque nada no serviço como nesse caso da qtd
            //caso queira que o usuário digite o valor de venda e custo somente abaixo os dois campos
            */
            iv.setItvPrecoVenda(iv.getProduto().getProPrecoVenda());
            iv.setItvPrecoCusto(iv.getProduto().getProPrecoCusto());
            iv.setVenda(obj);
        }
        itemVendaRepository.saveAll(obj.getItens());
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
        entity.setVendedor(obj.getVendedor());
        entity.setFpagamento(obj.getFpagamento());
        entity.setVenData(obj.getVenData());
        entity.setVenDesconto(obj.getVenDesconto());
        entity.setVenAcrescimo(obj.getVenAcrescimo());
        entity.setVenStatus(obj.getVenStatus());
        entity.setVenTipo(obj.getVenTipo());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}