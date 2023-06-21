/* INTRODUÇÃO A CLASSE USUÁRIO DTO.
 * Classe DTO, ou Data Transfer Object. Objeto de transferencia de dados. 
 * Criamos essa classe quando precisamos reduzir a quantidade de dados trafegados em rede.
 * como por exemplo, não listar os pedidos no qual estão os usuários. Então criamos essa 
 * classe contendo somente os atributos que vamos trafegar em rede.
 * 
 * Apos a criação da classe devemos alterar também o metodo listagem geral de usuário
 * nos pacotes resource e services.
 */

package com.erp.controle.financeiro.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.erp.controle.financeiro.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO implements Serializable{
	
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
	
	@JsonIgnore //ignora a listagem de senha por questão de segurança	
	@Getter
	@Setter
	private String USU_SENHA;

	public UsuarioDTO() {		
	}		
	
	public UsuarioDTO(Usuario obj) {
		USU_ID = obj.getUSU_ID();
		USU_NOME = obj.getUSU_NOME();
		USU_FLAG = obj.getUSU_FLAG();
		USU_FUNCAO = obj.getUSU_FUNCAO();
		USU_SENHA = obj.getUSU_SENHA();
	}
}