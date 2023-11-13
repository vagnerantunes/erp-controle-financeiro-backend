package com.erp.controle.financeiro.dto;

import com.erp.controle.financeiro.entities.Fornecedor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FornecedorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long forId;
    private String forRazaoSocial;
    private String forFantasia;
    private String forCnpj;
    private String forFlag;
    private String forObs;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Fornecedor obj) {
        forId = obj.getForId();
        forRazaoSocial = obj.getForRazaoSocial();
        forFantasia = obj.getForFantasia();
        forCnpj = obj.getForCnpj();
        forFlag = obj.getForFlag();
        forObs = obj.getForObs();
    }
}