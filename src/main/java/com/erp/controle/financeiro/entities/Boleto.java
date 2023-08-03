package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "tb_boleto")
public class Boleto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOL_ID")
	private Long bolId;
	
	@Getter
	@Setter
	@Column(name = "BOL_DESCRICAO")
	private String bolDescricao;
	
	@Getter
	@Setter
	@Column(name = "BOL_DATA")
	private Date bolData;
	
	@Getter
	@Setter
	@Column(name = "BOL_VALOR")
	private Double bolValor;
	
	@Getter
	@Setter
	@Column(name = "BOL_QTD_PARCELA")
	private Integer bolQtdParcela;
	
	@Getter
	@Setter
	@Column(name = "BOL_CONTA_PF_PJ")
	private String bolContaPfPj;
	
	@Getter
	@Setter
	@Column(name = "BOL_OBS")
	private String bolObs;
	
	@Getter	
	@JsonIgnore
	@OneToMany(mappedBy = "id.boleto")
	private Set<ParcelaBoleto> parcelaBoletos = new HashSet<>();

	public Boleto() {
	}

	public Boleto(Long bolId, String bolDescricao, Date bolData, Double bolValor, Integer bolQtdParcela,
			String bolContaPfPj, String bolObs) {
		super();
		this.bolId = bolId;
		this.bolDescricao = bolDescricao;
		this.bolData = bolData;
		this.bolValor = bolValor;
		this.bolQtdParcela = bolQtdParcela;
		this.bolContaPfPj = bolContaPfPj;
		this.bolObs = bolObs;
	}
}