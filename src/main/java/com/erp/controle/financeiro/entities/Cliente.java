package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @Column(length = 45, name = "CLI_NOME")
    private String cliNome;

    @Getter
    @Setter
    @Column(length = 15, name = "CLI_TIPO")
    private String cliTipo;

    @Getter
    @Setter
    @Column(length = 15, name = "CLI_CFP_CNPJ")
    private String cliCpfCnpj;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @Column(name = "CLI_DATA_NASCIMENTO")
    private Date cliDataNascimento;

    @Getter
    @Setter
    @Column(length = 15, name = "CLI_CONTATO")
    private String cliContato;

    @Getter
    @Setter
    @Column(length = 45, name = "CLI_EMAIL")
    private String cliEmail;

    @Getter
    @Setter
    @Column(length = 45, name = "CLI_RAZAO_SOCIAL")
    private String cliRazaoSocial;

    @Getter
    @Setter
    @Column(length = 45, name = "CLI_SEGMENTO")
    private String cliSegmento;

    @Getter
    @Setter
    @Column(length = 1, name = "CLI_FLAG")
    private String cliFlag;

    // Lista não tem Set, somente Get.
    @Getter
    @JsonIgnore
    @OneToMany(mappedBy = "clientes")
    private List<Venda> vendas = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(Long cliId, String cliNome, String cliTipo, String cliCpfCnpj, Date cliDataNascimento,
                   String cliContato, String cliEmail, String cliRazaoSocial,
                   String cliSegmento, String cliFlag) {
        super();
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliTipo = cliTipo;
        this.cliCpfCnpj = cliCpfCnpj;
        this.cliDataNascimento = cliDataNascimento;
        this.cliContato = cliContato;
        this.cliEmail = cliEmail;
        this.cliRazaoSocial = cliRazaoSocial;
        this.cliSegmento = cliSegmento;
        this.cliFlag = cliFlag;
    }
}