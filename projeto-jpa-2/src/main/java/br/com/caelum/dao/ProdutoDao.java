package br.com.caelum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.model.Produto;

@Repository
public class ProdutoDao {

	@PersistenceContext
	private EntityManager em;

//	public List<Produto> getProdutos() {
//		return em.createQuery("select distinct p from Produto p join fetch p.categorias", Produto.class).getResultList();
//	}
	
	/**
	 * 
	 * @getProdutos with EntityGraph
	 */
	
	public List<Produto> getProdutos() {
        return em.createQuery("select distinct p from Produto p", Produto.class)
        		.setHint("org.hibernate.cacheable", "true")/** cache this query**/
                .setHint("javax.persistence.loadgraph", em.getEntityGraph("produtoComCategoria"))
                .getResultList();
}

	public Produto getProduto(Integer id) {
		Produto produto = em.find(Produto.class, id);
		return produto;
	}
	
	/**
	 *  Query criada com Criteria do JPA2
	 * 
	 * @param nome
	 * @param categoriaId
	 * @param lojaId
	 * @return
	 */
	public List<Produto> getProdutosCriteriaJpa(String nome, Integer categoriaId,
	        Integer lojaId) {

	    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<Produto> query = criteriaBuilder
	            .createQuery(Produto.class);
	    Root<Produto> root = query.from(Produto.class);

	    Path<String> nomePath = root.get("nome");
	    Path<Integer> categoriaPath = root.join("categorias").get("id");
	    Path<Integer> lojaPath = root.get("loja").get("id");

	    List<Predicate> predicates = new ArrayList<>();

	    if (!nome.isEmpty()) {
	        Predicate nomeIgual = criteriaBuilder.like(nomePath, "%" + nome + "%");
	        predicates.add(nomeIgual);
	    }

	    if (categoriaId != null) {
	        Predicate categoriaIgual = criteriaBuilder.equal(categoriaPath,
	                categoriaId);
	        predicates.add(categoriaIgual);
	    }

	    if (lojaId != null) {
	        Predicate lojaIgual = criteriaBuilder.equal(lojaPath, lojaId);
	        predicates.add(lojaIgual);
	    }

	    query.where((Predicate[]) predicates.toArray(new Predicate[0]));

	    TypedQuery<Produto> typedQuery = em.createQuery(query);

	    return typedQuery.getResultList();

	}
	/**
	 * Query utilizando Criteria do Hibernate
	 * @param nome
	 * @param categoriaId
	 * @param lojaId
	 * @return
	 */
	@Transactional
	public List<Produto> getProdutos(String nome, Integer categoriaId, Integer lojaId) {
	    Session session = em.unwrap(Session.class);
	    Criteria criteria = session.createCriteria(Produto.class);

	    if (!nome.isEmpty()) {
	        criteria.add(Restrictions.like("nome", "%" + nome + "%"));
	    }

	    if (lojaId != null) {
	        criteria.add(Restrictions.like("loja.id", lojaId));
	    }

	    if (categoriaId != null) {
	        criteria.setFetchMode("categorias", FetchMode.JOIN)
	            .createAlias("categorias", "c")
	            .add(Restrictions.like("c.id", categoriaId));
	    }

	    return (List<Produto>) criteria.list();
	}

	public void insere(Produto produto) {
		if (produto.getId() == null)
			em.persist(produto);
		else
			em.merge(produto);
	}

}
