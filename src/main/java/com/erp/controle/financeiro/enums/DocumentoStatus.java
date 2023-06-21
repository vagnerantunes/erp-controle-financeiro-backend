package com.erp.controle.financeiro.enums;

public enum DocumentoStatus {
	
	ABERTO(1),
	PENDENTE(2),
	FINALIZADO(3),
	CANCELADO(4);
	
	private int codigo;
	
	private DocumentoStatus(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
		
	}
	
	public static DocumentoStatus valueOf(int codigo) {
		for (DocumentoStatus value : DocumentoStatus.values()) {
			if (value.getCodigo() == codigo) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código DocumentoStatus inválido");
	}
}