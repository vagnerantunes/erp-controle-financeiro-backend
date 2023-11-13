package com.erp.controle.financeiro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "CON_ID")
    private Long conId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_FOR_ID")
    private Fornecedor conForId;

    @Getter
    @Setter
    @Column(length = 4, name = "CON_DDD")
    private String conDDD;

    @Getter
    @Setter
    @Column(length = 10, name = "CON_NUMERO")
    private String conNumero;

    @Getter
    @Setter
    @Column(length = 45, name = "CON_EMAIL")
    private String conEmail;

    @Getter
    @Setter
    @Column(length = 100, name = "CON_OBS")
    private String conObs;

    public Contato() {
    }
    public Contato(Long conId, Fornecedor conForId, String conDDD, String conNumero, String conEmail, String conObs) {
        this.conId = conId;
        this.conForId = conForId;
        this.conDDD = conDDD;
        this.conNumero = conNumero;
        this.conEmail = conEmail;
        this.conObs = conObs;
    }
}