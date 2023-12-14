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
    @Column(name = "ENF_RUA", length = 55, nullable = false)
    private String enfRua;

    @Getter
    @Setter
    @Column(name = "ENF_NUMERO", nullable = false)
    private Integer enfNumero;

    @Getter
    @Setter
    @Column(name = "ENF_BAIRRO", length = 55, nullable = false)
    private String enfBairro;

    @Getter
    @Setter
    @Column(name = "ENF_CIDADE", length = 55, nullable = false)
    private String enfCidade;

    @Getter
    @Setter
    @Column(name = "ENF_CEP", length = 9, nullable = false)
    private String enfCep;


    @Getter
    @Setter
    @Column(name = "ENF_PAIS", length = 14, nullable = false)
    private String enfPais;

    @Getter
    @Setter
    @Column(name = "ENF_ESTADO", length = 20, nullable = false)
    private String enfEstado;

    @Getter
    @Setter
    @Column(name = "ENF_TIPO_RESIDENCIA", length = 18,  nullable = false)
    private String enfTipoResidencia;

    @Getter
    @Setter
    @Column(name = "ENF_COMPLEMENTO", length = 55,  nullable = false)
    private String enfComplemento;

    public EnderecoFornecedor(Long enfId, @NotNull Fornecedor enfForId, @NotNull String enfRua, @NotNull Integer enfNumero, @NotNull String enfBairro, @NotNull String enfCidade, @NotNull String enfCep, @NotNull String enfPais, @NotNull String enfEstado, @NotNull String enfTipoResidencia, @NotNull String enfComplemento) {
        this.enfId = enfId;
        this.enfForId = enfForId;
        this.enfRua = enfRua;
        this.enfNumero = enfNumero;
        this.enfBairro = enfBairro;
        this.enfCidade = enfCidade;
        this.enfCep = enfCep;
        this.enfPais = enfPais;
        this.enfEstado = enfEstado;
        this.enfTipoResidencia = enfTipoResidencia;
        this.enfComplemento = enfComplemento;
    }
}
