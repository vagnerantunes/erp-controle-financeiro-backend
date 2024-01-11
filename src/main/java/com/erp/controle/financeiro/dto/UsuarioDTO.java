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
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long usuId;

	@NotEmpty(message="Preenchimento obrigatório")
	private String usuNome;

	private String usuFlag;

	@NotEmpty(message="Preenchimento obrigatório")
	private String usuFuncao;

	@NotEmpty(message="Preenchimento obrigatório")
	private String usuSenha;
	public UsuarioDTO() {
	}

	
}