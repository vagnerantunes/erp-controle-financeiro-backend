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
	@Column(name = "FOR_NOME")
	private String forNome;
	
	@Getter
	@Setter	
	@Column(name = "FOR_TIPO")
	private String forTipo;
	
	@Getter
	@Setter	
	@Column(name = "FOR_CPF_CNPJ")
	private String forCpfCnpj;
	
	@Getter
	@Setter	
	@Column(name = "FOR_FANTASIA")
	private String forFantasia;
	
	@Getter
	@Setter
	@Column(name = "FOR_ENDERECO")
	private String forEndereco;
	
	@Getter
	@Setter
	@Column(name = "FOR_CONTATO")
	private String forContato;
	
	@Getter
	@Setter
	@Column(name = "FOR_EMAIL")
	private String forEmail;
	
	@Getter
	@Setter
	@Column(name = "FOR_FLAG")
	private String forFlag;


	public Fornecedor() {
		super();
	}


	public Fornecedor(Long forId, String forNome, String forTipo, String forCpfCnpj, String forFantasia,
			String forEndereco, String forContato, String forEmail, String forFlag) {
		super();
		this.forId = forId;
		this.forNome = forNome;
		this.forTipo = forTipo;
		this.forCpfCnpj = forCpfCnpj;
		this.forFantasia = forFantasia;
		this.forEndereco = forEndereco;
		this.forContato = forContato;
		this.forEmail = forEmail;
		this.forFlag = forFlag;
	}

}