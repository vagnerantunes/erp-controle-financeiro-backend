package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PRO_ID;
	
	@Getter
	@Setter
	private String PRO_DESCRICAO;
	
	@Getter
	@Setter
	private Double PRO_PRECOCUSTO;
	
	@Getter
	@Setter
	private Double PRO_PRECODEVENDA;
	
	@Getter
	@Setter
	private Double PRO_ESTOQUE;
	
	@Getter
	@Setter
	private Double PRO_ESTVENDIDO;
	
	@Getter
	@Setter
	private String PRO_FLAG;	
	
	@Getter
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemVenda> itemVendas = new HashSet<>();
	
	@Getter
	@OneToMany(mappedBy = "id.compra")	
	private Set<ItemCompra> itemCompras = new HashSet<>();

	public Produto() {		
	}

	public Produto(Long pRO_ID, String pRO_DESCRICAO, Double pRO_PRECOCUSTO, Double pRO_PRECODEVENDA,
			Double pRO_ESTOQUE, Double pRO_ESTVENDIDO, String pRO_FLAG) {
		super();
		PRO_ID = pRO_ID;
		PRO_DESCRICAO = pRO_DESCRICAO;
		PRO_PRECOCUSTO = pRO_PRECOCUSTO;
		PRO_PRECODEVENDA = pRO_PRECODEVENDA;
		PRO_ESTOQUE = pRO_ESTOQUE;
		PRO_ESTVENDIDO = pRO_ESTVENDIDO;
		PRO_FLAG = pRO_FLAG;
	}
	
	@JsonIgnore
	public Set<Venda> getVendas(){
		Set<Venda> set = new HashSet<>();
		for(ItemVenda x : itemVendas) {
			set.add(x.getVenda());
		}
		return set;
	}
	
	@JsonIgnore
	public Set<Compra> getCompras(){
		Set<Compra> set = new HashSet<>();
		for(ItemCompra x : itemCompras) {
			set.add(x.getCompra());
		}
		return set;
	}	
}