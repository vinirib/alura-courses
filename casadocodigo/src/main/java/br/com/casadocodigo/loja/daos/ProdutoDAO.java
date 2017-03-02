package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.infra.FileLoader;
import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private FileLoader fileLoader;
	
	public void gravar(Produto produto){
		manager.persist(produto);
	}
	
	public List<Produto> listar(){
		List<Produto> produtos = manager.createQuery("select q from Produto q", Produto.class).getResultList();
		for (Produto produto2 : produtos) {
			String image = fileLoader.load(produto2.getSumarioPath());
			produto2.setImageFile(image);
		}
		return produtos;
	}

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class)
				.setParameter("id", id).getSingleResult();
	}
	
	public void deletar(Integer id){
		Produto produto = manager.find(Produto.class, id);
		manager.remove(produto);
	}
	
	public void atualizar(Produto produtoUpdated){
		manager.merge(produtoUpdated);
	}
}
