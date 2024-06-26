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

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Entity
@Table(name = "tb_formaPagamento")
public class FormaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FPG_ID")
	private Long fpgId;
	
	@Getter
	@Setter
	@Column(name = "FPG_TIPO_CONTA")
	private String fpgTipoConta;
	
	@Getter
	@Setter
	@Column(name = "FPG_DESCRICAO")
	private String fpgDescricao;
	
	@Getter
	@Setter
	@Column(name = "FPG_TIPO")
	private String fpgTipo;
	
	@Getter
	@Setter
	@Column(name = "FPG_QTD_PARCELA")
	private Integer fpgQtdParcela;
	
	@Getter
	@Setter
	@Column(name = "FPG_PORCENTAGEM")
	private Double fpgPorcentagem;
	
	@Getter
	@Setter
	@Column(name = "FPG_FLAG")
	private String fpgFlag;
	
	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "pagamentos")
	private List<Venda> vendas = new ArrayList<>();
	
	public FormaPagamento() {
		super();
	}

	public FormaPagamento(Long fpgId, String fpgTipoConta, String fpgDescricao, String fpgTipo, Integer fpgQtdParcela,
			Double fpgPorcentagem, String fpgFlag) {
		super();
		this.fpgId = fpgId;
		this.fpgTipoConta = fpgTipoConta;
		this.fpgDescricao = fpgDescricao;
		this.fpgTipo = fpgTipo;
		this.fpgQtdParcela = fpgQtdParcela;
		this.fpgPorcentagem = fpgPorcentagem;
		this.fpgFlag = fpgFlag;
	}
	
}