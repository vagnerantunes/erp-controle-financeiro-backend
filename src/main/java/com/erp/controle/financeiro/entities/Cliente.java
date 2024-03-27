package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "CLI_ID")
    private Long cliId;

    @Getter
    @Setter
    @OneToMany(mappedBy = "encCliId", cascade = CascadeType.ALL)
    private List<EnderecoCliente> enderecoClientes = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "cclCliId", cascade = CascadeType.ALL)
    private List<ContatoCliente> contatoClientes = new ArrayList<>();

    @Getter
    @Setter
    @Column(length = 55, name = "CLI_NOME", nullable = false)
    private String cliNome;

    @Getter
    @Setter
    @Column(length = 15, name = "CLI_TIPO")
    private String cliTipo;

    @Getter
    @Setter
    @Column(length = 14, name = "CLI_CPF")
    private String cliCpf;

    @Getter
    @Setter
    @Column(length = 18, name = "CLI_CNPJ")
    private String cliCnpj;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @Column(name = "CLI_DATA_NASCIMENTO")
    private Date cliDataNascimento;

    @Getter
    @Setter
    @Column(length = 9, name = "CLI_FLAG")
    private String cliFlag = "ATIVO";

//    // Lista não tem Set, somente Get.
//    @Getter
//    @JsonIgnore
//    @OneToMany(mappedBy = "clientes")
//    private List<Venda> vendas = new ArrayList<>();

    public Cliente() {
    }
    public Cliente(Long cliId, @NotNull String cliNome, String cliTipo, String cliCpf, String cliCnpj, Date cliDataNascimento) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliTipo = cliTipo;
        this.cliCpf = cliCpf;
        this.cliCnpj = cliCnpj;
        this.cliDataNascimento = cliDataNascimento;
    }
}