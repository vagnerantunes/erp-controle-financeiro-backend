package com.erp.controle.financeiro.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "END_ID")
    private Long endId;

    // Cliente, fornecedor (o usuário ganha um endereço como cliente automaticamente)
    @Getter
    @Setter
    @Column(length = 45, name = "END_TIPO")
    private String endTipo;

    

    @Getter
    @Setter
    @Column(length = 45, name = "END_RUA")
    private String endRua;

    @Getter
    @Setter
    @Column(name = "END_NUMERO")
    private Integer endNumero;

    @Getter
    @Setter
    @Column(length = 45, name = "END_BAIRRO")
    private String endBairro;

    @Getter
    @Setter
    @Column(length = 45, name = "END_CIDADE")
    private String endCidade;

    @Getter
    @Setter
    @Column(length = 45, name = "END_ESTADO")
    private String endEstado;

    @Getter
    @Setter
    @Column(length = 9, name = "END_CEP")
    private String endCep;

    @Getter
    @Setter
    @Column(length = 45, name = "END_PAIS")
    private String endPais;



    @Getter
    @Setter
    @Column(length = 100, name = "END_OBS")
    private String endObs;

}
