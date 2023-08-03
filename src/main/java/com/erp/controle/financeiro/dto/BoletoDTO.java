package com.erp.controle.financeiro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BoletoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long bolId;
    private String bolDescricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date bolData;

    private Double bolValor;
    private Integer bolQtdParcela;
    private String bolContaPfPj;
    private String bolObs;

    public BoletoDTO() {
    }

    public BoletoDTO(Long bolId, String bolDescricao, Date bolData, Double bolValor, Integer bolQtdParcela, String bolContaPfPj, String bolObs) {
        this.bolId = bolId;
        this.bolDescricao = bolDescricao;
        this.bolData = bolData;
        this.bolValor = bolValor;
        this.bolQtdParcela = bolQtdParcela;
        this.bolContaPfPj = bolContaPfPj;
        this.bolObs = bolObs;
    }
}
