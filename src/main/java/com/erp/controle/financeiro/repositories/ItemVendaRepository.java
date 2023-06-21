package com.erp.controle.financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.controle.financeiro.entities.ItemVenda;
import com.erp.controle.financeiro.entities.pk.ItemVendaPK;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, ItemVendaPK>{
	
}
