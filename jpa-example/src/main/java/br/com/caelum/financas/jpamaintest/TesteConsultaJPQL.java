package br.com.caelum.financas.jpamaintest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaJPQL {
	
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Conta conta = new Conta();
		conta.setId(1);
		
//		Query query = manager.createQuery("select m from Movimentacao m where m.conta =?1 and m.tipoMovimentacao = ?2 ");
//        query.setParameter(1, conta);
//        query.setParameter(2, TipoMovimentacao.SAIDA);
		
        Query query = manager
                .createQuery("select m from Movimentacao m where m.conta = :pConta"
                        + " and m.tipoMovimentacao = :pTipo"
                        + " order by m.valor desc");

        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);
        
		List<Movimentacao> movimentacoes = 	query.getResultList();
		
		movimentacoes.forEach(m -> 
		System.out.println("\nDescricao ..: " + m.getDescricao()+ "\n Valor ......: R$ " + m.getValor()));

		manager.close();
	}

}
