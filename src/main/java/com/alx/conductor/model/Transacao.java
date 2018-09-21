package com.alx.conductor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Classe responsável por mapear a entidade Transacao do banco de dados.
 * @author ximenes
 */
@Entity
@Table(name="transacao")
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transacao")
	private Integer idTransacao;
	
    @Column(name="id_conta")
	private Integer idConta;
	
    @Column(name="valor")
	private BigDecimal valor;
	
    @Column(name="tipo_operacao")
	private String tipoOperacao;
	
    @Column(name="data_transacao")
	private Date dataTransacao;

	public Transacao() {

	}

	public Transacao(Integer idConta, BigDecimal valor, String tipoOperacao, Date dataTransacao) {
		super();
		this.setIdConta(idConta);
		this.setValor(valor);
		this.setTipoOperacao(tipoOperacao);
		this.setDataTransacao(dataTransacao);
	}
	
	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = (valor != null) ? valor : BigDecimal.ZERO;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

}
