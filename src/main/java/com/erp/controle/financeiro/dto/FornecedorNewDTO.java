package com.erp.controle.financeiro.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FornecedorNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String forRazaoSocial;
    private String forFantasia;
    private String forCnpj;
    private String forFlag;
    private String forObs;
    private String enfRua;
    private Integer enfNumero;
    private String enfBairro;
    private String enfCidade;
    private String enfEstado;
    private String enfCep;
    private String enfPais;
    private String enfObs;

    public FornecedorNewDTO() {
    }

}