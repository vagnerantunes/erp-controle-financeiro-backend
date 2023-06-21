package com.erp.controle.financeiro.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.erp.controle.financeiro.entities.Produto;
import com.erp.controle.financeiro.entities.Venda;

import lombok.Data;

@Embeddable
@Data
public class ItemVendaPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "venda_id")
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;	
	
}