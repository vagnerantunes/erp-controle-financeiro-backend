package com.erp.controle.financeiro.enums;

public enum VendaStatus {
	
	PEDIDO(1),
	ORCAMENTO(2);
	
	private int codigo;
	
	private VendaStatus(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public static VendaStatus valueOf(int codigo) {
		for(VendaStatus value : VendaStatus.values()) {
			if(value.getCodigo() == codigo) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código VendaStatus inválido");
	}
}