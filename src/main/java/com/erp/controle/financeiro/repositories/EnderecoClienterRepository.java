package com.erp.controle.financeiro.repositories;

import com.erp.controle.financeiro.entities.EnderecoCliente;
import com.erp.controle.financeiro.entities.EnderecoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoClienterRepository extends JpaRepository<EnderecoCliente, Long> {

}
