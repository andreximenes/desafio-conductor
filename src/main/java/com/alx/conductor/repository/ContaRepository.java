package com.alx.conductor.repository;

import org.springframework.data.repository.CrudRepository;

import com.alx.conductor.model.Conta;

public interface ContaRepository extends CrudRepository<Conta, Integer>{
	
	public Conta findByIdPessoa(Integer idPessoa);
}
