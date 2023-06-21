package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.erp.controle.financeiro.enums.PagamentoStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_fatura")
public class Fatura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAT_ID")
	private Long fatId;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "FAT_FPG_ID")
	private FormaPagamento fatFpgId;
	
	@Getter
	@Setter
	@Column(name = "FAT_DATA_LANCAMENTO")
	private Instant fatDataLancamento;
	
	@Getter
	@Setter
	@Column(name = "FAT_VALOR_TOTAL")
	private Double fatValorTotal;
	
	@Getter
	@Setter
	@Column(name = "FAT_DESCONTO")
	private Double fatDesconto;
	
	@Getter
	@Setter
	@Column(name = "FAT_JUROS")
	private Double fatJuros;
	
	@Getter
	@Setter
	@Column(name = "FAT_VALOR_PAGO")
	private Double fatValorPago;
	
	@Getter
	@Setter
	@Column(name = "FAT_VENCIMENTO")
	private Instant fatVencimento;
	
	@Getter
	@Setter
	@Column(name = "FAT_ATRASO")
	private Integer fatAtraso;
	
	@Column(name = "FAT_STS_PAG")
	private Integer fatStsPag;	
	
	@Getter
	@OneToMany(mappedBy = "id.fatura")
	private Set<ParcelaBoleto> parcelaBoletos = new HashSet<>();

	public Fatura() {
	}
	
	public Fatura(Long fatId, FormaPagamento fatFpgId, Instant fatDataLancamento, Double fatValorTotal,
			Double fatDesconto, Double fatJuros, Double fatValorPago, Instant fatVencimento, Integer fatAtraso,
			Integer fatStsPag) {
		super();
		this.fatId = fatId;
		this.fatFpgId = fatFpgId;
		this.fatDataLancamento = fatDataLancamento;
		this.fatValorTotal = fatValorTotal;
		this.fatDesconto = fatDesconto;
		this.fatJuros = fatJuros;
		this.fatValorPago = fatValorPago;
		this.fatVencimento = fatVencimento;
		this.fatAtraso = fatAtraso;
		this.fatStsPag = fatStsPag;
	}	

	public PagamentoStatus getFatStsPag() {
		return PagamentoStatus.valueOf(fatStsPag);
		
	}

	public void setFatStsPag(PagamentoStatus fAT_STS_PAG) {
		if(fAT_STS_PAG != null) {
			this.fatStsPag = fAT_STS_PAG.getCodigo();
		}
	}
	
}