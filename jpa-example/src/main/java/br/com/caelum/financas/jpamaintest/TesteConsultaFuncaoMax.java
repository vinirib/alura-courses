package br.com.caelum.financas.jpamaintest;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncaoMax {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		
		Conta conta = new Conta();
		conta.setId(1);
		BigDecimal maximo = manager.createQuery("select max(m.valor) from Movimentacao m where m.conta.id =:contaId", BigDecimal.class)
				.setParameter("contaId", 1).getSingleResult();
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(manager);

		Double mediaDaconta = movimentacaoDao.mediaDaconta(conta);
		manager.close();
		System.out.println(maximo);
		System.out.println(mediaDaconta);
	}

}
