package com.erp.controle.financeiro.dto;

import com.erp.controle.financeiro.entities.Fornecedor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CompraDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comId;

    private Long comForId;

    private Date comData;

    private Double comValorTotal;

    private Double comDesconto;

    private Double comJuros;

    private Integer comStsDoc;

    public CompraDTO() {
    }

    public CompraDTO(Long comId, Long comForId, Date comData, Double comValorTotal, Double comDesconto, Double comJuros, Integer comStsDoc) {
        this.comId = comId;
        this.comForId = comForId;
        this.comData = comData;
        this.comValorTotal = comValorTotal;
        this.comDesconto = comDesconto;
        this.comJuros = comJuros;
        this.comStsDoc = comStsDoc;
    }


}