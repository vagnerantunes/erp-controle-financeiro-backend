package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable{		
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VEN_ID")
	private Long venId;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "id.venda")
	private Set<ItemVenda> itens = new HashSet<>();

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="vendedor_id")
	private Vendedor vendedor;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="fpagamento_id")
	private FormaPagamento fpagamento;

	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@Column(name = "VEN_DATA")
	private Date venData = new Date();
	
	@Getter
	@Setter
	@Column(name = "VEN_DESCONTO")
	private Double venDesconto;
	
	@Getter
	@Setter
	@Column(name = "VEN_ACRESCIMO")
	private Double venAcrescimo;

	//pendente, finalizado, cancelado
	@Getter
	@Setter
	@Column(name = "VEN_STATUS")
	private String venStatus;

	//orcamento, pedido
	@Getter
	@Setter
	@Column(name = "VEN_TIPO")
	private String venTipo;
	public Venda() {
	}
	public Venda(Long venId, Cliente cliente, FormaPagamento fpagamento, Vendedor vendedor,
				 Double venDesconto, Double venAcrescimo, String venStatus, String venTipo) {
		this.venId = venId;
		this.cliente = cliente;
		this.fpagamento = fpagamento;
		this.vendedor = vendedor;
		this.venDesconto = venDesconto;
		this.venAcrescimo = venAcrescimo;
		this.venStatus = venStatus;
		this.venTipo = venTipo;
	}

	public Double getTotalVenda() {
		Double sum = 0.0;
		for (ItemVenda x : itens) {
			sum = sum + x.getTotalVenda();
		}
		sum = ((sum + venAcrescimo) - venDesconto);
		return sum;
	}

	public Double getTotalCusto() {
		Double sum = 0.0;
		for (ItemVenda x : itens) {
			sum = sum + x.getTotalCusto();
		}
		return sum;
	}
}