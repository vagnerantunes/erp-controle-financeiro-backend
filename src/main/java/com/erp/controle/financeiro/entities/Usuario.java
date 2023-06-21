package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
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
@Table(name = "tb_usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long USU_ID;
	
	@Column(length = 45, nullable = false)
	@Getter
	@Setter
	private String USU_NOME;
	
	@Column(length = 1, nullable = false)
	@Getter
	@Setter
	private String USU_FLAG;
	
	@Column(length = 15, nullable = false)
	@Getter
	@Setter
	private String USU_FUNCAO;
	
	//@JsonIgnore //anotação para não ficar aparecendo dados referente a senha	
	@Getter
	@Setter
	private String USU_SENHA;
	
	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "usuarios")
	private List<Usuario> usuarios = new ArrayList<>();
	
	public Usuario() {			
	}			

	public Usuario(Long uSU_ID, String uSU_NOME, String uSU_FLAG, String uSU_FUNCAO, String uSU_SENHA) {
		super();
		USU_ID = uSU_ID;
		USU_NOME = uSU_NOME;
		USU_FLAG = uSU_FLAG;
		USU_FUNCAO = uSU_FUNCAO;
		USU_SENHA = uSU_SENHA;
	}	
}