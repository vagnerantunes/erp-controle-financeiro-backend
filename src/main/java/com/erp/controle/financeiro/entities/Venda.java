package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.erp.controle.financeiro.enums.PagamentoStatus;
import com.erp.controle.financeiro.enums.VendaStatus;

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
	@JoinColumn(name="fpagamento_id")
	private FormaPagamento fpagamento;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="vendedor_id")
	private Vendedor vendedor;
	
	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@Column(name = "VEN_DATA")
	private Date venData;
	
	@Getter
	@Setter
	@Column(name = "VEN_DESCONTO")
	private Double venDesconto;
	
	@Getter
	@Setter
	@Column(name = "VEN_JUROS")
	private Double venJuros;

	@Getter
	@Setter
	@Column(name = "VEN_STATUS")
	private String venStatus;
	public Venda() {
	}
	public Venda(Long venId, Cliente cliente, FormaPagamento fpagamento, Vendedor vendedor,Date venData,
				 Double venDesconto, Double venJuros, String venStatus) {
		this.venId = venId;
		this.cliente = cliente;
		this.fpagamento = fpagamento;
		this.vendedor = vendedor;
		this.venData = venData;
		this.venDesconto = venDesconto;
		this.venJuros = venJuros;
		this.venStatus = venStatus;
	}

	public Double getTotal() {
		Double sum = 0.0;
		for (ItemVenda x : itens) {
			sum = sum + x.getTotalVenda();
		}
		return sum;

	}
}