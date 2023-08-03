package com.erp.controle.financeiro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class FornecedorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long forId;
    private String forNome;
    private String forTipo;
    private String forCpfCnpj;
    private String forFantasia;
    private String forEndereco;
    private String forContato;
    private String forEmail;
    private String forFlag;

    public FornecedorDTO() {
    }
    public FornecedorDTO(Long forId, String forNome, String forTipo, String forCpfCnpj, String forFantasia, String forEndereco, String forContato, String forEmail, String forFlag) {
        this.forId = forId;
        this.forNome = forNome;
        this.forTipo = forTipo;
        this.forCpfCnpj = forCpfCnpj;
        this.forFantasia = forFantasia;
        this.forEndereco = forEndereco;
        this.forContato = forContato;
        this.forEmail = forEmail;
        this.forFlag = forFlag;
    }

}