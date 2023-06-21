package com.erp.controle.financeiro.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.erp.controle.financeiro.entities.Boleto;
import com.erp.controle.financeiro.entities.Fatura;

import lombok.Data;

@Embeddable
@Data
public class ParcelaBoletoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "fatura_id")
	private Fatura fatura;
	
	@ManyToOne
	@JoinColumn(name = "boleto_id")
	private Boleto boleto;

}
