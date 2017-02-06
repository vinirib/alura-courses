package br.com.caelum.financas.jpamaintest;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaConsultaFuncaoCount {
	
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Conta conta = manager.find(Conta.class, 2);
		Long result = manager.createQuery("select count(m) from Movimentacao m where m.conta.id = :contaId", Long.class).setParameter("contaId", 1).getSingleResult();
		manager.close();
		System.out.println(result);
	}

}
