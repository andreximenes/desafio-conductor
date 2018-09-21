package com.alx.conductor.rest;

import java.io.Serializable;
import java.util.List;

import com.alx.conductor.model.Conta;
import com.alx.conductor.model.Pessoa;
import com.alx.conductor.model.Transacao;

public class InformacaoContaResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Conta conta;
	private List<Transacao> extrato;
	
	public InformacaoContaResponse() {}
	
	public InformacaoContaResponse(Conta conta, Pessoa proprietario, List<Transacao> extrato) {
		super();
		this.conta = conta;
		this.extrato = extrato;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Transacao> getExtrato() {
		return extrato;
	}

	public void setExtrato(List<Transacao> extrato) {
		this.extrato = extrato;
	}
	
}
