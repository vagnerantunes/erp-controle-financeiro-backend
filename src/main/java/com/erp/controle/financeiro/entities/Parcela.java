package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.erp.controle.financeiro.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_parcela")
public class Parcela implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	private Long PVD_ID;
	@Getter
	@Setter
	private Integer PVD_PARCELA;
	@Getter
	@Setter
	private Double PVD_VRPARCELA;
	@Getter
	@Setter
	private Double PVD_VRPAGO;
	@Getter
	@Setter
	private Instant PVD_DTVENCIMENTO;
	@Getter
	@Setter
	private Integer PVD_ATRASO;
	@Getter
	@Setter
	private PagamentoStatus PVD_SITUACAO;
	
	@Getter
	@JsonIgnore	
	@ManyToMany(mappedBy = "parcelas")
	private Set<Venda> pedidos = new HashSet<>();	
	
	public Parcela() {		
	}

	public Parcela(Long pVD_ID, Integer pVD_PARCELA, Double pVD_VRPARCELA, Double pVD_VRPAGO, Instant pVD_DTVENCIMENTO,
			Integer pVD_ATRASO, PagamentoStatus pVD_SITUACAO) {
		super();
		PVD_ID = pVD_ID;
		PVD_PARCELA = pVD_PARCELA;
		PVD_VRPARCELA = pVD_VRPARCELA;
		PVD_VRPAGO = pVD_VRPAGO;
		PVD_DTVENCIMENTO = pVD_DTVENCIMENTO;
		PVD_ATRASO = pVD_ATRASO;
		PVD_SITUACAO = pVD_SITUACAO;		
	}
	
	
}