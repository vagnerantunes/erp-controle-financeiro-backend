package com.erp.controle.financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.controle.financeiro.entities.ParcelaBoleto;
import com.erp.controle.financeiro.entities.pk.ParcelaBoletoPK;

@Repository
public interface ParcelaBoletoRepository extends JpaRepository<ParcelaBoleto, ParcelaBoletoPK>{

}