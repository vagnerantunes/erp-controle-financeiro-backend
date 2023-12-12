package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOR_ID")
    private Long forId;

    @Getter
    @Setter
    @OneToMany(mappedBy = "enfForId", cascade = CascadeType.ALL)
    private List<EnderecoFornecedor> enderecos = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "conForId", cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    //para deixar o campo null, habilite no atribute da entidade, no dto, e no construtor da entidade
    @Getter
    @Setter
    @Column(name = "FOR_RAZAO_SOCIAL", nullable = false, length = 55)
    private String forRazaoSocial;

    @Getter
    @Setter
    @Column(name = "FOR_NOME_FANTASIA", nullable = false, length = 55)
    private String forNomeFantasia;

    @Getter
    @Setter
    @Column(name = "FOR_CNPJ", nullable = false, length = 18, unique = true)
    private String forCnpj;

    /* ANOTAÇÃO CAMPO PADRÃO "ATIVO"
     *
     * Aqui ao criar um novo fornecedor (post), já é criado com o valor padrao "ativo".
     * Não coloque o campo (forFlag) no construtor
     */
    @Getter
    @Setter
    @Column(name = "FOR_FLAG", length = 9)
    private String forFlag = "ATIVO";
    public Fornecedor(Long forId, @NotNull String forRazaoSocial,  @NotNull String forNomeFantasia,  @NotNull String forCnpj) {
        this.forId = forId;
        this.forRazaoSocial = forRazaoSocial;
        this.forNomeFantasia = forNomeFantasia;
        this.forCnpj = forCnpj;
    }
}