package com.erp.controle.financeiro.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.controle.financeiro.entities.pk.ItemVendaPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_item_venda")
public class ItemVenda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemVendaPK id = new ItemVendaPK();
	
	@Getter
	@Setter
	private Double VPR_QTD;
	
	@Getter
	@Setter
	private Double VPR_PRECOVENDA;
	
	@Getter
	@Setter
	private Double VPR_PRECOCUSTO;
	
	public ItemVenda() {	
	}

	public ItemVenda(Venda venda, Produto produto, Double VPR_QTD, Double VPR_PRECOVENDA, Double VPR_PRECOCUSTO) {
		super();
		id.setVenda(venda);
		id.setProduto(produto);
		this.VPR_QTD = VPR_QTD;
		this.VPR_PRECOVENDA = VPR_PRECOVENDA;
		this.VPR_PRECOCUSTO = VPR_PRECOCUSTO;
	}
	
	@JsonIgnore
	public Venda getVenda() {
		return id.getVenda();
	}
	
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}
	
	@JsonIgnore
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	public Double getSubTotal() {
		return VPR_PRECOVENDA * VPR_QTD;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((VPR_PRECOCUSTO == null) ? 0 : VPR_PRECOCUSTO.hashCode());
		result = prime * result + ((VPR_PRECOVENDA == null) ? 0 : VPR_PRECOVENDA.hashCode());
		result = prime * result + ((VPR_QTD == null) ? 0 : VPR_QTD.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (VPR_PRECOCUSTO == null) {
			if (other.VPR_PRECOCUSTO != null)
				return false;
		} else if (!VPR_PRECOCUSTO.equals(other.VPR_PRECOCUSTO))
			return false;
		if (VPR_PRECOVENDA == null) {
			if (other.VPR_PRECOVENDA != null)
				return false;
		} else if (!VPR_PRECOVENDA.equals(other.VPR_PRECOVENDA))
			return false;
		if (VPR_QTD == null) {
			if (other.VPR_QTD != null)
				return false;
		} else if (!VPR_QTD.equals(other.VPR_QTD))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;		
	}
	
}