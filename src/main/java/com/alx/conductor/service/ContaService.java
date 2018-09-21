package com.alx.conductor.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alx.conductor.exception.OperacaoException;
import com.alx.conductor.exception.TipoErro;
import com.alx.conductor.model.Conta;
import com.alx.conductor.repository.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private PessoaService pessoaService;
	
	public Conta getContaByPessoa(Integer idPessoa) {
		return contaRepository.findByIdPessoa(idPessoa);
	}
	
	public Optional<Conta> findById(Integer idConta) {
		return contaRepository.findById(idConta);
	}
	
	@Transactional
	public Conta novaConta(Conta conta) throws OperacaoException {
		if(pessoaService.existsById(conta.getIdPessoa())) {
			return contaRepository.save(conta);
		} else {
			throw new OperacaoException(TipoErro.PESSOA_INEXISTENTE);
		}
	}
	
	public boolean existsById(Integer idConta) {
		return contaRepository.existsById(idConta);
	}
	
	public Conta realizaOperacao(Conta conta) {
		return contaRepository.save(conta);
	}
}
