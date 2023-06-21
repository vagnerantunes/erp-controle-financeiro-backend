package com.erp.controle.financeiro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.erp.controle.financeiro.enums.DocumentoStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_compra")
public class Compra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_ID")
	private Long comId;	

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "COM_FOR_ID")
	private Fornecedor comForId;		
			
	@Getter
	@Setter
	@Column(name = "COM_DATA")
	private Instant comData;
	
	@Getter
	@Setter
	@Column(name = "COM_VALOR_TOTAL")
	private Double comValorTotal;
	
	@Getter
	@Setter
	@Column(name = "COM_DESCONTO")
	private Double comDesconto;
	
	@Getter
	@Setter
	@Column(name = "COM_JUROS")
	private Double comJuros;
	
	@Column(name = "COM_STS_DOC")
	private Integer comStsDoc;
	
	@Getter
	@OneToMany(mappedBy = "id.compra")
	private Set<ItemCompra> itemCompras = new HashSet<>();	
	
	public Compra() {		
	}

	public Compra(Long comId, Fornecedor comForId, Instant comData, Double comValorTotal, Double comDesconto,
			Double comJuros, Integer comStsDoc) {
		super();
		this.comId = comId;
		this.comForId = comForId;
		this.comData = comData;
		this.comValorTotal = comValorTotal;
		this.comDesconto = comDesconto;
		this.comJuros = comJuros;
		this.comStsDoc = comStsDoc;
	}

	public DocumentoStatus getComStsDoc() {
		return DocumentoStatus.valueOf(comStsDoc);
		
	}

	public void setComStsDoc(DocumentoStatus cOM_STS_DOC) {
		if(cOM_STS_DOC != null) {
			this.comStsDoc = cOM_STS_DOC.getCodigo();
		}
	}
	
}