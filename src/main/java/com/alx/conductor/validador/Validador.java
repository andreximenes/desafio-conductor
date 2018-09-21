package com.alx.conductor.validador;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alx.conductor.exception.OperacaoException;
import com.alx.conductor.exception.TipoErro;
import com.alx.conductor.model.Conta;
import com.alx.conductor.service.ContaService;
import com.alx.conductor.service.TransacaoService;

@Component
public class Validador {
	@Autowired
	private ContaService contaService;
	@Autowired
	private TransacaoService transacaoService;
	
	public boolean isContaValida(Integer idConta) throws OperacaoException {
		return contaService.existsById(idConta);
	}
	
	public boolean isContaAtiva(Conta conta) throws OperacaoException {
		if(conta.isAtiva()) {
			return true;
		}
		throw new OperacaoException(TipoErro.CONTA_BLOQUEDA);
	}
	
	public boolean isValorValido(BigDecimal valor) throws OperacaoException {
		if(valor.compareTo(BigDecimal.ZERO) > 0) {
			return true;
		} else {
			throw new OperacaoException(TipoErro.VALOR_INVALIDO_PARA_OPERACOES);
		}
	}

	public boolean isSaquePermitido(Conta conta, BigDecimal valorSaque) throws OperacaoException {
		BigDecimal total  = transacaoService.getTotalSaqueDiario(conta.getIdConta());
		if((total.compareTo(conta.getLimiteSaqueDiario()) < 0) || (valorSaque.compareTo(conta.getLimiteSaqueDiario()) <= 0)) {
			return true;
		} else {
			throw new OperacaoException(TipoErro.LIMITE_SAQUE_EXCEDIDO);
		}
	}
}
