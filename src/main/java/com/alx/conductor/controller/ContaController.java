package com.alx.conductor.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alx.conductor.exception.OperacaoException;
import com.alx.conductor.model.Conta;
import com.alx.conductor.model.TipoOperacao;
import com.alx.conductor.rest.OperacaoContaRequest;
import com.alx.conductor.rest.InformacaoContaResponse;
import com.alx.conductor.service.ContaService;
import com.alx.conductor.service.OperacaoContaService;

/**
 * Classe responsavel por receber todas as requisições referente a Conta
 * @author ximenes
 *
 */
@Controller
@RequestMapping(value = "/api/conta", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaController {

	@Autowired
	private ContaService contaService;
	@Autowired
	private OperacaoContaService opService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Conta> create(@RequestBody @Valid Conta conta) throws OperacaoException {
		return ResponseEntity.ok(contaService.novaConta(conta));
	}
	
	@RequestMapping(value = "/{idConta}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Conta>> findById(@PathVariable("idConta") Integer idConta) {
        return ResponseEntity.ok(contaService .findById(idConta));
    }
	
	@RequestMapping(value = "/saque", method = RequestMethod.POST)
    public ResponseEntity<InformacaoContaResponse> saque(@RequestBody OperacaoContaRequest request) throws OperacaoException {
        return ResponseEntity.ok(opService.realizaOperacaoConta(request, TipoOperacao.SAQUE));
    }
	
	@RequestMapping(value = "/deposito", method = RequestMethod.POST)
    public ResponseEntity<InformacaoContaResponse> deposito(@RequestBody OperacaoContaRequest request) throws OperacaoException {
        return ResponseEntity.ok(opService.realizaOperacaoConta(request, TipoOperacao.DEPOSITO));
    }
	
	@RequestMapping(value = "/extrato", method = RequestMethod.POST)
    public ResponseEntity<InformacaoContaResponse> extrato(@RequestBody OperacaoContaRequest request) throws OperacaoException {
		TipoOperacao tipoOperacao = TipoOperacao.EXTRATO_DIARIO;
		if(request.getDataInicio() != null && request.getDataFim() != null) {
			tipoOperacao = TipoOperacao.EXTRATO_PERIODO;
		}
        return ResponseEntity.ok(opService.realizaOperacaoConta(request, tipoOperacao));
    }
	
	@RequestMapping(value = "/bloqueio", method = RequestMethod.POST)
    public ResponseEntity<InformacaoContaResponse> bloqueio(@RequestBody OperacaoContaRequest request) throws OperacaoException{
        return ResponseEntity.ok(opService.realizaOperacaoConta(request, TipoOperacao.BLOQUEIO));
    }
	
	@RequestMapping(value = "/desbloqueio", method = RequestMethod.POST)
    public ResponseEntity<InformacaoContaResponse> desbloqueio(@RequestBody OperacaoContaRequest request) throws OperacaoException{
        return ResponseEntity.ok(opService.realizaOperacaoConta(request, TipoOperacao.DESBLOQUEIO));
    }
}
