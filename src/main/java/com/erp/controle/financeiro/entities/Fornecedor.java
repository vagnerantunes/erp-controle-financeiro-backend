package com.erp.controle.financeiro.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FOR_ID")
	private Long forId;
	
	@Getter
	@Setter	
	@Column(name = "FOR_RAZAO_SOCIAL")
	private String forRazaoSocial;

	@Getter
	@Setter
	@Column(name = "FOR_FANTASIA")
	private String forFantasia;
	
	@Getter
	@Setter	
	@Column(name = "FOR_CNPJ")
	private String forCnpj;

	@Getter
	@Setter
	@Column(name = "FOR_OBS")
	private String forObs;
	
	@Getter
	@Setter
	@Column(name = "FOR_FLAG")
	private String forFlag;
	public Fornecedor() {
	}

	public Fornecedor(Long forId, String forRazaoSocial, String forFantasia, String forCnpj, String forFlag) {
		this.forId = forId;
		this.forRazaoSocial = forRazaoSocial;
		this.forFantasia = forFantasia;
		this.forCnpj = forCnpj;
		this.forFlag = forFlag;
	}

}