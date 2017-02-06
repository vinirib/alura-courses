package br.com.caelum.financas.jpamaintest;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class JpaTest {

	public static void main(String[] args) {

		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		
		Conta conta = entityManager.find(Conta.class, 1);
		System.out.println(conta.getTitular());
//		entityManager.persist(new Conta("Vinicius", "HSBC", "0929", "0548-8"));
		conta.setTitular("Roberto Alves");
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

}
