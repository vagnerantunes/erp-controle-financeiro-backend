package com.erp.controle.financeiro.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.erp.controle.financeiro.entities.Compra;
import com.erp.controle.financeiro.entities.Produto;

import lombok.Data;

@Embeddable
@Data
public class ItemCompraPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
}