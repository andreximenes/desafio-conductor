package com.alx.conductor.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alx.conductor.model.TipoOperacao;
import com.alx.conductor.model.Transacao;
import com.alx.conductor.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRespository;
	
	public Transacao save(Transacao transacao) {
		return transacaoRespository.save(transacao);
	}
	
	public List<Transacao> getExtratoDoDia(Integer idConta){
		return transacaoRespository.getTransacoesDiaCorrente(idConta);
	}
	
	public List<Transacao> getExtratoPorPeriodo(Integer idConta, Date dataIni, Date dataFim){
		return transacaoRespository.getTransacoesPorContaDataInicialAndFinal(idConta, dataIni, dataFim);
	}
	
	public BigDecimal getTotalSaqueDiario(Integer idConta) {
		BigDecimal total = transacaoRespository.getTotalSaqueDiario(idConta, TipoOperacao.SAQUE.getDatabaseValue()); 
		return (total != null) ? total : BigDecimal.ZERO;
	}
}
