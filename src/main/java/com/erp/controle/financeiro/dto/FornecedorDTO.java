package com.erp.controle.financeiro.dto;

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

    public FornecedorDTO(Long forId, String forRazaoSocial, String forFantasia, String forCnpj, String forFlag, String forObs) {
        this.forId = forId;
        this.forRazaoSocial = forRazaoSocial;
        this.forFantasia = forFantasia;
        this.forCnpj = forCnpj;
        this.forFlag = forFlag;
        this.forObs = forObs;
    }
}