package com.erp.controle.financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.controle.financeiro.entities.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long>{
	
}
