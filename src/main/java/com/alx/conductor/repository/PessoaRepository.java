package com.alx.conductor.repository;

import org.springframework.data.repository.CrudRepository;

import com.alx.conductor.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

}
