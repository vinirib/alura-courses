package br.com.caelum.financas.jpamaintest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsulta {
	
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Conta conta = new Conta();
		conta.setId(1);
		
		Query query = manager.createQuery("select m from Movimentacao m where m.conta.id = " +conta.getId());
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
		movimentacoes.forEach(m -> 
		System.out.println("\nDescricao ..: " + m.getDescricao()+ "\n Valor ......: R$ " + m.getValor()));

		manager.close();
	}

}
