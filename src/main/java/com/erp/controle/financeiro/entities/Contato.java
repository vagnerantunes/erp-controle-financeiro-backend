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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_CLI_ID", nullable = false)
    private Cliente concLIId;

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

    @Getter
    @Setter
    @Column(length = 10, name = "CON_TIPO_REDE1")
    private String conTipoRede1;

    @Getter
    @Setter
    @Column(length = 55, name = "CON_REDE_SOCIAL1")
    private String conRedeSocial1;

    @Getter
    @Setter
    @Column(length = 10, name = "CON_TIPO_REDE2")
    private String conTipoRede2;

    @Getter
    @Setter
    @Column(length = 55, name = "CON_REDE_SOCIAL2")
    private String conRedeSocial2;

    public Contato(Long conId, Fornecedor conForId, Cliente concLIId ,String conTelefoneComercial, @NotNull String conCelular, String conEmail, String conEmailSecundario, String conTipoRede1, String conRedeSocial1, String conTipoRede2, String conRedeSocial2) {
        this.conId = conId;
        this.conForId = conForId;
        this.concLIId = concLIId;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conCelular = conCelular;
        this.conEmail = conEmail;
        this.conEmailSecundario = conEmailSecundario;
        this.conTipoRede1 = conTipoRede1;
        this.conRedeSocial1 = conRedeSocial1;
        this.conTipoRede2 = conTipoRede2;
        this.conRedeSocial2 = conRedeSocial2;
    }
}