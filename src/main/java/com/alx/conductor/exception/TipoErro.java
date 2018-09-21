package com.alx.conductor.exception;

/**
 * Tipos de erros mapeados e lançados através da OperacaoException.class
 * @author ximenes
 *
 */
public enum TipoErro {
	
	SALDO_INSUFICIENTE("Saldo Insuficiente"),    
	LIMITE_SAQUE_EXCEDIDO("O limite de saque diário foi excedido"),
	CONTA_INVALIDA_INEXISTENTE("Conta inválida ou inexistente"),
	CONTA_BLOQUEDA("A conta econtra-se bloqueada"),
	PESSOA_INEXISTENTE("Pessoa inexistente no banco de dados"),
	VALOR_INVALIDO_PARA_OPERACOES("O valor passado na operação é inválido");

	private String mensagem;

	TipoErro(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}


}
