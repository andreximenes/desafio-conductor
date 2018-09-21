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

import com.alx.conductor.model.Pessoa;
import com.alx.conductor.rest.CriaPessoaRequest;
import com.alx.conductor.service.PessoaService;
/**
 * Classe responsável por receber requisicoes referente a Pessoa
 * @author ximenes
 *
 */
@Controller
@RequestMapping(value = "/api/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pessoa> incluir(@RequestBody @Valid CriaPessoaRequest request) {
		return ResponseEntity.ok(pessoaService.insert(request.getPessoa()));
	}
	
	@RequestMapping(value = "/{idPessoa}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Pessoa>> findById(@PathVariable("idPessoa") Integer idPessoa){
        return ResponseEntity.ok(pessoaService.getOne(idPessoa));
    }

}
