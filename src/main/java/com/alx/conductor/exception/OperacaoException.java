package com.alx.conductor.exception;

/**
 * Excecao que é lançada dentro das funcionalidades e operacoes do sistema. 
 * Cada excecao tem um tipo de erro para indentificar a causa.
 * @author ximenes
 *
 */
public class OperacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private TipoErro erro;

	public OperacaoException(TipoErro erro) {
		super(erro.getMensagem());
		this.erro = erro;
	}
	
	public OperacaoException(String msg) {
		super(msg);
	}
	
	

}
