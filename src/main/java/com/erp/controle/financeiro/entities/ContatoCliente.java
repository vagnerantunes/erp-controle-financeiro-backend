package com.erp.controle.financeiro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "tb_contato_cliente")
public class ContatoCliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "CCL_ID")
    private Long cclId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CCL_CLI_ID", nullable = false)
    private Cliente cclCliId;

    @Getter
    @Setter
    @Column(name = "CCL_CELULAR", length = 15, nullable = false)
    private String cclCelular;

    @Getter
    @Setter
    @Column(length = 55, name = "CCL_EMAIL")
    private String cclEmail;

    @Getter
    @Setter
    @Column(length = 10, name = "CCL_TIPO_REDE1")
    private String cclTipoRede1;

    @Getter
    @Setter
    @Column(length = 55, name = "CCL_REDE_SOCIAL1")
    private String cclRedeSocial1;

    public ContatoCliente(Long cclId, Cliente cclCliId, String cclCelular, String cclEmail, String cclTipoRede1, String cclRedeSocial1) {
        this.cclId = cclId;
        this.cclCliId = cclCliId;
        this.cclCelular = cclCelular;
        this.cclEmail = cclEmail;
        this.cclTipoRede1 = cclTipoRede1;
        this.cclRedeSocial1 = cclRedeSocial1;
    }
}