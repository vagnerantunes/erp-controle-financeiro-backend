package com.erp.controle.financeiro.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_vendedor")
public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "VND_ID")
    private Long vndId;

    @Getter
    @Setter
    @Column(length = 55, name = "VND_NOME", nullable = false)
    private String vndNome;

    @Getter
    @Setter
    @Column(length = 20, name = "VND_CARGO")
    private String vndCargo;

    @Getter
    @Setter
    @Column(length = 20, name = "VND_STATUS")
    private String vndStatus  = "ATIVO";

    @Getter
    @Setter
    @Column(length = 55, name = "VND_OBS")
    private String vndObs;
    public Vendedor() {
    }
    public Vendedor(Long vndId, String vndNome, String vndCargo, String vndObs) {
        this.vndId = vndId;
        this.vndNome = vndNome;
        this.vndCargo = vndCargo;
        this.vndObs = vndObs;
    }

}