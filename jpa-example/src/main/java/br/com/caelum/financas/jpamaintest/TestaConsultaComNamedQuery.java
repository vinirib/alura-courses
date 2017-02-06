package br.com.caelum.financas.jpamaintest;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaConsultaComNamedQuery {
	
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Conta conta = new Conta();
		conta.setId(1);
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(manager);
		long totalDeMovimentacoes = movimentacaoDao.totalDeMovimentacoes(conta);
		manager.close();
		System.out.println(totalDeMovimentacoes);
	}

}
