package br.com.caelum.financas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.model.Conta;

public class MovimentacaoDao {

	private EntityManager manager;

	public MovimentacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public Double mediaDaconta(Conta conta) {
		TypedQuery<Double> query = 
	            manager.createQuery("select avg(m.valor) from Movimentacao m where m.conta=:pConta "
	                            + " and m.tipoMovimentacao = 'SAIDA'", Double.class);
	    query.setParameter("pConta", conta);
	    return query.getSingleResult();
	}
	
	public long totalDeMovimentacoes(Conta conta){
	    TypedQuery<Long> query = manager.createNamedQuery("totalDeMovimentacoes", Long.class);
	    query.setParameter("pConta", conta);
	    return query.getSingleResult();
	}

}
