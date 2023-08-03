package com.erp.controle.financeiro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cliId;
    private String cliNome;
    private String cliTipo;
    private String cliCpfCnpj;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date cliDataNascimento;

    private String cliContato;
    private String cliEmail;
    private String cliRazaoSocial;
    private String cliSegmento;
    private String cliFlag;

    public ClienteDTO() {
    }

    public ClienteDTO(Long cliId, String cliNome, String cliTipo, String cliCpfCnpj, Date cliDataNascimento, String cliContato, String cliEmail, String cliRazaoSocial, String cliSegmento, String cliFlag) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliTipo = cliTipo;
        this.cliCpfCnpj = cliCpfCnpj;
        this.cliDataNascimento = cliDataNascimento;
        this.cliContato = cliContato;
        this.cliEmail = cliEmail;
        this.cliRazaoSocial = cliRazaoSocial;
        this.cliSegmento = cliSegmento;
        this.cliFlag = cliFlag;
    }
}
