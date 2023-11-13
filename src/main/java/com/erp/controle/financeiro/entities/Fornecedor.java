package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
	@OneToMany(mappedBy="enfForId", cascade=CascadeType.ALL)
	private List<EnderecoFornecedor> enderecos = new ArrayList<>();
	
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
	@Column(name = "FOR_FLAG")
	private String forFlag;

	@Getter
	@Setter
	@Column(name = "FOR_OBS")
	private String forObs;
	

	public Fornecedor() {
	}

	public Fornecedor(Long forId, String forRazaoSocial, String forFantasia, String forCnpj, String forFlag,
					  String forObs) {
		this.forId = forId;
		this.forRazaoSocial = forRazaoSocial;
		this.forFantasia = forFantasia;
		this.forCnpj = forCnpj;
		this.forFlag = forFlag;
		this.forObs = forObs;
	}

}