package com.erp.controle.financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.controle.financeiro.entities.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long>{

}
