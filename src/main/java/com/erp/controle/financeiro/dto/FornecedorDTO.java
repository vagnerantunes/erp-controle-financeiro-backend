package com.erp.controle.financeiro.dto;

import com.erp.controle.financeiro.entities.Fornecedor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class FornecedorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long forId;

    @NotEmpty(message="Preenchimento obrigatório")
    private String forRazaoSocial;

    @NotEmpty(message="Preenchimento obrigatório")
    private String forNomeFantasia;

    @NotEmpty(message="Preenchimento obrigatório")
    private String forCnpj;

    private String forFlag;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Fornecedor obj) {
        forId = obj.getForId();
        forRazaoSocial = obj.getForRazaoSocial();
        forNomeFantasia = obj.getForNomeFantasia();
        forCnpj = obj.getForCnpj();
        forFlag = obj.getForFlag();
    }
}