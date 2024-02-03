package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	private Long proId;
	
	@Getter
	@Setter
	@Column(name = "PRO_DESCRICAO", nullable = false, length = 100)
	private String proDescricao;
	
	@Getter
	@Setter
	@Column(name = "PRO_PRECO_CUSTO", nullable = false)
	private Double proPrecoCusto;
	
	@Getter
	@Setter
	@Column(name = "PRO_PRECO_VENDA")
	private Double proPrecoVenda;
	
	@Getter
	@Setter
	@Column(name = "PRO_ESTOQUE")
	private Double proEstoque;
	
	@Getter
	@Setter
	@Column(name = "PRO_ESTOQUE_VENDIDO")
	private Double proEstoqueVendido;
	
	@Getter
	@Setter
	@Column(name = "PRO_FLAG", length = 9)
	private String proFlag = "ATIVO";
	
//	@Getter
//	@OneToMany(mappedBy = "id.produto")
//	private Set<ItemVenda> itemVendas = new HashSet<>();
//
//	@Getter
//	@OneToMany(mappedBy = "id.compra")
//	private Set<ItemCompra> itemCompras = new HashSet<>();

	public Produto(Long proId, @NotNull String proDescricao, @NotNull Double proPrecoCusto, Double proPrecoVenda, Double proEstoque, Double proEstoqueVendido) {
		this.proId = proId;
		this.proDescricao = proDescricao;
		this.proPrecoCusto = proPrecoCusto;
		this.proPrecoVenda = proPrecoVenda;
		this.proEstoque = proEstoque;
		this.proEstoqueVendido = proEstoqueVendido;
	}

//	@JsonIgnore
//	public Set<Venda> getVendas(){
//		Set<Venda> set = new HashSet<>();
//		for(ItemVenda x : itemVendas) {
//			set.add(x.getVenda());
//		}
//		return set;
//	}
//
//	@JsonIgnore
//	public Set<Compra> getCompras(){
//		Set<Compra> set = new HashSet<>();
//		for(ItemCompra x : itemCompras) {
//			set.add(x.getCompra());
//		}
//		return set;
//	}
}