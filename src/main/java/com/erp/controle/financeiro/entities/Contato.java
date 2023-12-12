package com.erp.controle.financeiro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
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
    @JoinColumn(name = "CON_FOR_ID", nullable = false)
    private Fornecedor conForId;

    @Getter
    @Setter
    @Column(name = "CON_TELEFONE_COMERCIAL", length = 14)
    private String conTelefoneComercial;

    @Getter
    @Setter
    @Column(name = "CON_CELULAR", length = 15, nullable = false)
    private String conCelular;

    @Getter
    @Setter
    @Column(length = 55, name = "CON_EMAIL")
    private String conEmail;

    @Getter
    @Setter
    @Column(length = 55, name = "CON_EMAIL_SECUNDARIO")
    private String conEmailSecundario;

    public Contato(Long conId, Fornecedor conForId, String conTelefoneComercial, @NotNull String conCelular, String conEmail, String conEmailSecundario) {
        this.conId = conId;
        this.conForId = conForId;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conCelular = conCelular;
        this.conEmail = conEmail;
        this.conEmailSecundario = conEmailSecundario;
    }
}