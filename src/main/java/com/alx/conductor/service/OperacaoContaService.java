package com.alx.conductor.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alx.conductor.exception.OperacaoException;
import com.alx.conductor.exception.TipoErro;
import com.alx.conductor.model.Conta;
import com.alx.conductor.model.TipoOperacao;
import com.alx.conductor.model.Transacao;
import com.alx.conductor.rest.InformacaoContaResponse;
import com.alx.conductor.rest.OperacaoContaRequest;
import com.alx.conductor.validador.Validador;

@Service
public class OperacaoContaService {
	
	@Autowired
	private ContaService contaService;
	@Autowired
	private TransacaoService transacaoService;
	@Autowired
	private Validador validador;
	
	public InformacaoContaResponse realizaOperacaoConta(OperacaoContaRequest request, TipoOperacao tipoOperacao) throws OperacaoException {
		if(validador.isContaValida(request.getIdConta())) {
			Optional<Conta> result = contaService.findById(request.getIdConta());
			Conta conta = result.get();
			switch (tipoOperacao.getCode()) {

			case 1:
				//SAQUE
				saque(conta, request.getValor());
				return getExtratoDiario(conta, true);
			case 2:
				//DEPOSITO
				deposito(conta, request.getValor());
				return getExtratoDiario(conta, true);
			case 3:
				//BLOQUEIO
				bloquearConta(conta, false);
				return getExtratoDiario(conta, false);
			case 4:
				//DESBLOQUEIO
				bloquearConta(conta, true);
				return getExtratoDiario(conta, false);
			case 5:
				//EXTRATO_PERIODO
				return getExtratoPorPeriodo(conta, request.getDataInicio(), request.getDataFim());
			default:
				//EXTRATO_DIARIO
				return getExtratoDiario(conta, true);
			}
		} else {
			throw new OperacaoException(TipoErro.CONTA_INVALIDA_INEXISTENTE);
		}
	}
	
	@Transactional
	private void saque(Conta conta, BigDecimal valorSaque) throws OperacaoException {
		if(validador.isValorValido(valorSaque) && validador.isSaquePermitido(conta, valorSaque) && validador.isContaAtiva(conta)) {
			if(conta.getSaldo().compareTo(valorSaque) >= 0) {
				conta.setSaldo(conta.getSaldo().subtract(valorSaque));
				contaService.realizaOperacao(conta);
				transacaoService.save(new Transacao(conta.getIdConta(), valorSaque, TipoOperacao.SAQUE.getDatabaseValue(), new Date()));
			} else {
				throw new OperacaoException(TipoErro.SALDO_INSUFICIENTE);
			}
		} 
	}

	@Transactional
	private void deposito(Conta conta, BigDecimal valorDeposito) throws OperacaoException {
		if(validador.isValorValido(valorDeposito) && validador.isContaAtiva(conta)) {
			conta.setSaldo(conta.getSaldo().add(valorDeposito));
			contaService.realizaOperacao(conta);
			transacaoService.save(new Transacao(conta.getIdConta(), valorDeposito, TipoOperacao.DEPOSITO.getDatabaseValue(), new Date()));
		}
	}
	
	private void bloquearConta(Conta conta, boolean bloqueio) {
		conta.setAtiva(bloqueio);
		contaService.realizaOperacao(conta);
	}
	
	private InformacaoContaResponse getExtratoPorPeriodo(Conta conta, String dataIni, String dataFim) throws OperacaoException {
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		InformacaoContaResponse response = new InformacaoContaResponse();
		try {
			response.setConta(conta);
			response.setExtrato(transacaoService.getExtratoPorPeriodo(conta.getIdConta(), fmt.parse(dataIni), fmt.parse(dataFim)));
		} catch(Exception e) {
			throw new OperacaoException(e.getMessage());
		}
		
		return response;
	}
	
	private InformacaoContaResponse getExtratoDiario(Conta conta, boolean exibeTransacacoes) {
		InformacaoContaResponse response = new InformacaoContaResponse();
		response.setConta(conta);
		if(exibeTransacacoes) {
			response.setExtrato(transacaoService.getExtratoDoDia(conta.getIdConta()));
		} 
		return response;
	}
}
