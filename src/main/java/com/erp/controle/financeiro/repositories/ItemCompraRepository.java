package com.erp.controle.financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.controle.financeiro.entities.ItemCompra;
import com.erp.controle.financeiro.entities.pk.ItemCompraPK;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, ItemCompraPK>{

}
