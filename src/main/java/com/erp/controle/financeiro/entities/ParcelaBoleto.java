package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.controle.financeiro.entities.pk.ParcelaBoletoPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_parcela_boleto")
public class ParcelaBoleto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ParcelaBoletoPK id = new ParcelaBoletoPK();
	
	@Getter
	@Setter
	private Integer PBL_NPARCELA;
	
	@Getter
	@Setter
	private Double PBL_VALORPARCELA;
	
	@Getter
	@Setter
	private Instant PBL_VENCIMENTO;

	public ParcelaBoleto() {
	}

	public ParcelaBoleto(Fatura fatura, Boleto boleto, Integer pBL_NPARCELA, Double pBL_VALORPARCELA, Instant pBL_VENCIMENTO) {
		super();
		id.setFatura(fatura);
		id.setBoleto(boleto);	
		PBL_NPARCELA = pBL_NPARCELA;
		PBL_VALORPARCELA = pBL_VALORPARCELA;
		PBL_VENCIMENTO = pBL_VENCIMENTO;
	}
	
	@JsonIgnore
	public Fatura getFatura() {
		return id.getFatura();
	}
	
	public void setFatura(Fatura fatura) {
		id.setFatura(fatura);
	}
	
	@JsonIgnore
	public Boleto getBoleto() {
		return id.getBoleto();
	}
	
	public void setBoleto(Boleto boleto) {
		id.setBoleto(boleto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PBL_NPARCELA == null) ? 0 : PBL_NPARCELA.hashCode());
		result = prime * result + ((PBL_VALORPARCELA == null) ? 0 : PBL_VALORPARCELA.hashCode());
		result = prime * result + ((PBL_VENCIMENTO == null) ? 0 : PBL_VENCIMENTO.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParcelaBoleto other = (ParcelaBoleto) obj;
		if (PBL_NPARCELA == null) {
			if (other.PBL_NPARCELA != null)
				return false;
		} else if (!PBL_NPARCELA.equals(other.PBL_NPARCELA))
			return false;
		if (PBL_VALORPARCELA == null) {
			if (other.PBL_VALORPARCELA != null)
				return false;
		} else if (!PBL_VALORPARCELA.equals(other.PBL_VALORPARCELA))
			return false;
		if (PBL_VENCIMENTO == null) {
			if (other.PBL_VENCIMENTO != null)
				return false;
		} else if (!PBL_VENCIMENTO.equals(other.PBL_VENCIMENTO))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}			
}