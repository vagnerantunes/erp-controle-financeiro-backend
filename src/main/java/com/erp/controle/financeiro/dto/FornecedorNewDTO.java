package com.erp.controle.financeiro.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class FornecedorNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Preenchimento obrigatório")
    private String forRazaoSocial;

    @NotEmpty(message="Preenchimento obrigatório")
    private String forNomeFantasia;

    @CNPJ
    @NotEmpty(message="Preenchimento obrigatório")
    private String forCnpj;

    private String forFlag;

    @NotEmpty(message="Preenchimento obrigatório")
    private String enfRua;
    @NotNull(message = "Preenchimento obrigatório")
    private Integer enfNumero;

    @NotEmpty(message="Preenchimento obrigatório")
    private String enfBairro;
    @NotEmpty(message="Preenchimento obrigatório")
    private String enfCidade;
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve seguir o formato 12345-678")
    @NotEmpty(message="Preenchimento obrigatório")
    private String enfCep;
    @NotEmpty(message="Preenchimento obrigatório")
    private String enfEstado;

    @NotEmpty(message="Preenchimento obrigatório")
    private String enfComplemento;

    //quando o campo tem expressão e pode ser nulo, precisa de "$|" no inicio para dizer que é um campo não obrigatorio
    @Pattern(regexp = "^$|^\\(\\d{2}\\) \\d{4}-\\d{4}$", message = "O telefone deve seguir o formato (99) 9999-9999")
    private String conTelefoneComercial;

    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O celular deve seguir o formato (99) 99999-9999")
    @NotEmpty(message="Preenchimento obrigatório")
    private String conCelular;

    @Email
    private String conEmail;
    @Email
    private String conEmailSecundario;
    public FornecedorNewDTO() {
    }

}