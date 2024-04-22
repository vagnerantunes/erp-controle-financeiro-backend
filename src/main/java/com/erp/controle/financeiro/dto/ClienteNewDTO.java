package com.erp.controle.financeiro.dto;

import com.erp.controle.financeiro.entities.ContatoCliente;
import com.erp.controle.financeiro.entities.EnderecoCliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cliId;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cliNome;
    private String cliTipo;
    private String cliCpf;
    private String cliCnpj;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date cliDataNascimento;
    private String cliFlag;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String encRua;
    @NotNull
    private Integer encNumero;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String encBairro;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String encCidade;
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve seguir o formato 12345-678")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String encCep;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String encEstado;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String encComplemento;
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O celular deve seguir o formato (99) 99999-9999")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cclCelular;
    private String cclTelefoneComercial;
    @Email
    private String cclEmail;
    @Email
    private String cclEmailSecundario;
    public ClienteNewDTO() {
    }

}