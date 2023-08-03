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
	@Column(name = "USU_ID")
	private Long usuId;

	@Getter
	@Setter
	@Column(length = 45, nullable = false, name = "USU_NOME")
	private String usuNome;

	@Getter
	@Setter
	@Column(length = 1, nullable = false, name = "USU_FLAG")
	private String usuFlag;

	@Getter
	@Setter
	@Column(length = 15, nullable = false, name = "USU_FUNCAO")
	private String usuFuncao;

	//@JsonIgnore //anotação para não ficar aparecendo dados referente a senha
	@Getter
	@Setter
	@Column(name = "USU_SENHA")
	private String usuSenha;

	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "usuarios")
	private List<Usuario> usuarios = new ArrayList<>();

	public Usuario() {
	}

	public Usuario(Long usuId, String usuNome, String usuFlag, String usuFuncao, String usuSenha) {
		super();
		this.usuId = usuId;
		this.usuNome = usuNome;
		this.usuFlag = usuFlag;
		this.usuFuncao = usuFuncao;
		this.usuSenha = usuSenha;
	}
}