package com.alx.conductor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe responsável por mapear a entidade Pessoa do banco de dados.
 * @author ximenes
 */
@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pessoa")
	private Integer idPessoa;
	
	@NotBlank
	@Size(min=1, max=100)
	@Column(name="nome")
	private String nome;
	
	@NotBlank
	@Column(name="cpf", unique=true)
	@Size(max=11)
	private String cpf;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") 
	@Column(name="data_nascimento")
	private Date dataNascimento;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String cpf, Date dataNascimento) {
		super();
		this.setNome(nome);
		this.setCpf(cpf);
		this.setDataNascimento(dataNascimento);
	}


	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}
}
