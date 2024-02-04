package com.erp.controle.financeiro.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proId;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String proDescricao;
    @NotNull
    private Double proPrecoCusto;
    private Double proPrecoVenda;
    private Double proEstoque;
    private Double proEstoqueVendido;
    private String proFlag;
    public ProdutoDTO() {
    }
}
