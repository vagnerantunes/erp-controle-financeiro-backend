package com.erp.controle.financeiro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_end_fornecedor")
public class EnderecoFornecedor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "ENF_ID")
    private Long enfId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ENF_FOR_ID")
    private Fornecedor enfForId;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_RUA")
    private String enfRua;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_NUMERO")
    private Integer enfNumero;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_BAIRRO")
    private String enfBairro;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_CIDADE")
    private String enfCidade;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_ESTADO")
    private String enfEstado;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_CEP")
    private String enfCep;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_PAIS")
    private String enfPais;

    @Getter
    @Setter
    @Column(length = 45, name = "ENF_OBS")
    private String enfObs;

    public EnderecoFornecedor() {
    }

    public EnderecoFornecedor(Long enfId, Fornecedor enfForId, String enfRua, Integer enfNumero, String enfBairro, String enfCidade, String enfEstado, String enfCep, String enfPais, String enfObs) {
        this.enfId = enfId;
        this.enfForId = enfForId;
        this.enfRua = enfRua;
        this.enfNumero = enfNumero;
        this.enfBairro = enfBairro;
        this.enfCidade = enfCidade;
        this.enfEstado = enfEstado;
        this.enfCep = enfCep;
        this.enfPais = enfPais;
        this.enfObs = enfObs;
    }
}
