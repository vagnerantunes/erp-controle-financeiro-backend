package com.erp.controle.financeiro.entities;

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

}