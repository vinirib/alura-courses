package br.com.caelum.financas.jpamaintest;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoConta {
	
	public static void main(String[] args) {
	    EntityManager manager = new JPAUtil().getEntityManager();
        Conta conta = manager.createQuery("select c from Conta c join fetch c.movimentacoes where c.id = :contaId ", Conta.class)
        		.setParameter("contaId", 1).getSingleResult(); //id deve existir
        manager.close();
        System.out.println(conta.getMovimentacoes().size());
	}

}
