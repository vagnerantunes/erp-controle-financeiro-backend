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

	@JsonIgnore
	@Getter
	@Setter
	@EmbeddedId
	private ItemVendaPK id = new ItemVendaPK();
	
	@Getter
	@Setter
	private Double itvQtd;
	
	@Getter
	@Setter
	private Double itvPrecoVenda;
	
	@Getter
	@Setter
	private Double itvPrecoCusto;
	
	public ItemVenda() {	
	}

	public ItemVenda(Venda venda, Produto produto, Double itvQtd, Double itvPrecoVenda, Double itvPrecoCusto) {
		id.setVenda(venda);
		id.setProduto(produto);
		this.itvQtd = itvQtd;
		this.itvPrecoVenda = itvPrecoVenda;
		this.itvPrecoCusto = itvPrecoCusto;
	}

	@JsonIgnore
	public Venda getVenda() {
		return id.getVenda();
	}
	
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public Double getTotalVenda() {
		return itvPrecoVenda * itvQtd;
		
	}
}