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
@Table(name = "tb_end_cliente")
public class EnderecoCliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "ENC_ID")
    private Long encId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ENC_CLI_ID")
    private Cliente encCliId;

    @Getter
    @Setter
    @Column(name = "ENC_RUA", length = 55, nullable = false)
    private String encRua;

    @Getter
    @Setter
    @Column(name = "ENC_NUMERO", nullable = false)
    private Integer encNumero;

    @Getter
    @Setter
    @Column(name = "ENC_BAIRRO", length = 55, nullable = false)
    private String encBairro;

    @Getter
    @Setter
    @Column(name = "ENC_CIDADE", length = 55, nullable = false)
    private String encCidade;

    @Getter
    @Setter
    @Column(name = "ENC_CEP", length = 9, nullable = false)
    private String encCep;

    @Getter
    @Setter
    @Column(name = "ENC_ESTADO", length = 20, nullable = false)
    private String encEstado;

    @Getter
    @Setter
    @Column(name = "ENC_COMPLEMENTO", length = 150, nullable = false)
    private String encComplemento;

    public EnderecoCliente(Long encId, @NotNull Cliente encCliId,@NotNull String encRua, @NotNull Integer encNumero,
                           @NotNull String encBairro, @NotNull String encCidade, @NotNull String encCep,
                           @NotNull String encEstado, @NotNull String encComplemento) {
        this.encId = encId;
        this.encCliId = encCliId;
        this.encRua = encRua;
        this.encNumero = encNumero;
        this.encBairro = encBairro;
        this.encCidade = encCidade;
        this.encCep = encCep;
        this.encEstado = encEstado;
        this.encComplemento = encComplemento;
    }
}