package com.erp.controle.financeiro.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
implementacao de classe unica. tem somente a forma de pagamento
 */
@EqualsAndHashCode
@Entity
@Table(name = "tb_formaPagamento")
public class FormaPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @Getter
    @Setter
    @Column(name = "FPG_DESCRICAO", length = 50, nullable = false)
    private String fpgDescricao;

    @Getter
    @Setter
    @Column(name = "FPG_TIPO", length = 10, nullable = false)
    private String fpgTipo;

    @Getter
    @Setter
    @Column(name = "FPG_QTD_PARCELA", nullable = false)
    private Integer fpgQtdParcela;

    @Getter
    @Setter
    @Column(name = "FPG_APROVACAO", length = 3, nullable = false)
    private String fpgAprovacao;

    @Getter
    @Setter
    @Column(name = "FPG_ACRESCIMO_DESCONTO", length = 9)
    private String fpgTipoAcresDesc;

    @Getter
    @Setter
    @Column(name = "FPG_DESCRICAO_ACRES_DESC")
    private String fpgDescricaoAcreDesc;

    @Getter
    @Setter
    @Column(name = "FPG_VALOR")
    private Double fpgValor;

    @Getter
    @Setter
    @Column(name = "FPG_PORCENTAGEM")
    private Double fpgPorcentagem;

    @Getter
    @Setter
    @Column(name = "FPG_FLAG", length = 9)
    private String fpgFlag = "ATIVO";
    public FormaPagamento() {
        super();
    }

    public FormaPagamento(Long fpgId, @NotNull String fpgDescricao, @NotNull String fpgTipo, @NotNull Integer fpgQtdParcela, @NotNull String fpgAprovacao, String fpgTipoAcresDesc, String fpgDescricaoAcreDesc,Double fpgValor, Double fpgPorcentagem) {
        this.fpgId = fpgId;
        this.fpgDescricao = fpgDescricao;
        this.fpgTipo = fpgTipo;
        this.fpgQtdParcela = fpgQtdParcela;
        this.fpgAprovacao = fpgAprovacao;
        this.fpgTipoAcresDesc = fpgTipoAcresDesc;
        this.fpgDescricaoAcreDesc = fpgDescricaoAcreDesc;
        this.fpgPorcentagem = fpgPorcentagem;
        this.fpgValor = fpgValor;
    }
}