package com.alx.conductor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alx.conductor.model.Pessoa;
import com.alx.conductor.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa insert(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Optional<Pessoa> getOne(Integer idPessoa) {
		return pessoaRepository.findById(idPessoa);
	}
	
	public boolean existsById(Integer idPessoa) {
		return pessoaRepository.existsById(idPessoa);
	}
	
}
