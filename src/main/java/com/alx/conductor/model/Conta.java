package com.alx.conductor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

/**
 * Classe responsável por mapear a entidade Conta do banco de dados.
 * @author ximenes
 *
 */
@Entity
@Table(name="conta")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_conta")
	private Integer idConta;
	
	@Column(name="id_pessoa")
	private Integer idPessoa;
	
	@Column(name="saldo")
	private BigDecimal saldo;
	
	@Column(name="limite_saque_diario")
	private BigDecimal limiteSaqueDiario;
	
	@Column(name="ativa")
	private boolean ativa;
	
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	public Conta() {}
	
	public Conta(Integer idPessoa, @NotNull BigDecimal saldo, @NotNull BigDecimal limiteSaqueDiario, boolean ativa,
			Date dataCriacao) {
		super();
		this.idPessoa = idPessoa;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.ativa = ativa;
		this.dataCriacao = dataCriacao;
	}


	public Integer getIdConta() {
		return idConta;
	}



	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}



	public Integer getIdPessoa() {
		return idPessoa;
	}



	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}



	public BigDecimal getSaldo() {
		return saldo;
	}



	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}



	public BigDecimal getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}



	public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}



	public boolean isAtiva() {
		return ativa;
	}



	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}



	public Date getDataCriacao() {
		return dataCriacao;
	}



	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", idPessoa=" + idPessoa + ", saldo=" + saldo + ", limiteSaqueDiario="
				+ limiteSaqueDiario + ", ativo=" + ativa + ", dataCriacao=" + dataCriacao + "]";
	}
}
