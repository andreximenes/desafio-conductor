package com.alx.conductor.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alx.conductor.model.Pessoa;

public class CriaPessoaRequest {
	private String nome;
	private String cpf;
	private String dataNascimento;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Pessoa getPessoa() {
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Pessoa p = null;
		try {
			p = new Pessoa(nome, cpf, fmt.parse(dataNascimento));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return p;
	}
}
