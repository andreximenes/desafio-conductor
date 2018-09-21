package com.alx.conductor.model;

/**
 * Enum com todos os tipos de operacao utilizado para manipulação de contas.
 * @author ximenes
 *
 */
public enum TipoOperacao {

	SAQUE(1, "S"),    
	DEPOSITO(2, "D"),
	BLOQUEIO(3, "B"),
	DESBLOQUEIO(4,"U"),
	EXTRATO_PERIODO(5, "P"),
	EXTRATO_DIARIO(6, "E");

	private int code;
	private String databaseValue;

	TipoOperacao(int code, String databaseValue) {
		this.code = code;
		this.databaseValue = databaseValue;
	}

	public int getCode() {
		return code;
	}
	
	public String getDatabaseValue() {
		return databaseValue;
	}


}
