package br.com.caelum.financas.jpamaintest;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class JpaTestMembership {

	public static void main(String[] args) {

		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		Movimentacao movimentacao = createMovimentacao();
		Conta conta = createConta();
		movimentacao.setConta(conta);
		
		entityManager.persist(conta);
		entityManager.persist(movimentacao);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

	private static Conta createConta() {
		return new Conta("Vinicius", "HSBC", "0929", "0548-8");
	}

	private static Movimentacao createMovimentacao() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setData(Calendar.getInstance());
        movimentacao.setDescricao("Conta de Luz");
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setValor(new BigDecimal("123.9"));
		return movimentacao;
	}

}
