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
public class FormaPagamentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long fpgId;

    private String fpgTipoConta;
    private String fpgDescricao;
    private String fpgTipo;
    private Integer fpgQtdParcela;
    private Double fpgPorcentagem;
    private String fpgFlag;
    public FormaPagamentoDTO() {
    }
    public FormaPagamentoDTO(Long fpgId, String fpgTipoConta, String fpgDescricao, String fpgTipo, Integer fpgQtdParcela, Double fpgPorcentagem, String fpgFlag) {
        this.fpgId = fpgId;
        this.fpgTipoConta = fpgTipoConta;
        this.fpgDescricao = fpgDescricao;
        this.fpgTipo = fpgTipo;
        this.fpgQtdParcela = fpgQtdParcela;
        this.fpgPorcentagem = fpgPorcentagem;
        this.fpgFlag = fpgFlag;
    }
}