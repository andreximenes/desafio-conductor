package com.alx.conductor.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alx.conductor.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
	
	public List<Transacao> findAllByIdConta(Integer idConta);
	
	@Query(value = "SELECT id_transacao, id_conta, valor, tipo_operacao, data_transacao "
			+ "FROM transacao WHERE id_conta= :idConta AND date(data_transacao) = date(CURRENT_TIMESTAMP) ORDER BY id_transacao DESC", nativeQuery = true)
	public List<Transacao> getTransacoesDiaCorrente(@Param("idConta") Integer idConta);
	
	@Query(value = "SELECT sum(valor) AS total FROM transacao WHERE "
			+ "id_conta = :idConta AND tipo_operacao = :tipoOperacao AND date(data_transacao) = date(CURRENT_TIMESTAMP)", nativeQuery = true)
	public BigDecimal getTotalSaqueDiario(@Param("idConta") Integer idConta, @Param("tipoOperacao") String tipoOperacao);
	
	
	@Query(value = "SELECT id_transacao, id_conta, valor, tipo_operacao, data_transacao "
			+ "FROM transacao WHERE id_conta = :idConta AND date(data_transacao) BETWEEN date(:dataIni) and date(:dataFim)", nativeQuery = true)
	public List<Transacao> getTransacoesPorContaDataInicialAndFinal(@Param("idConta") Integer idConta, 
			@Param("dataIni") Date dataIni, @Param("dataFim") Date dataFim);

}
