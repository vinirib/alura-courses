package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.infra.FileLoader;
import br.com.casadocodigo.loja.models.Categoria;
import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private FileLoader fileLoader;
	
	public void gravar(Produto produto){
		if (produto.getId() == 0)
			manager.persist(produto);
		else
			manager.merge(produto);
	}
	
	public List<Produto> listar(){
		List<Produto> produtos = manager.createQuery("select q from Produto q", Produto.class).getResultList();
		setBase64Images(produtos);
		return produtos;
	}

	public List<Produto> listarLimitada(int quantidade){
		List<Produto> produtos = manager.createQuery("select q from Produto q order by rand()", Produto.class).setMaxResults(quantidade).getResultList();
		setBase64Images(produtos);
		return produtos;
	}


	public Produto find(Integer id) {
		Produto produto = manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class)
				.setParameter("id", id).getSingleResult();	
		String image = fileLoader.load(produto.getSumarioPath());
		produto.setImageFile(image);
		return produto;
	}
	
	public void deletar(Integer id){
		Produto produto = manager.find(Produto.class, id);
		manager.remove(produto);
	}
	
	public void atualizar(Produto produtoUpdated){
		manager.merge(produtoUpdated);
	}

	public List<Produto> listarPorCategoria(String categoria) {
		List<Produto> produtos = manager.createQuery("select p from Produto p join fetch p.categorias categorias where categorias = :categoria", Produto.class)
				.setParameter("categoria", Categoria.valueOf(categoria)).getResultList();
		setBase64Images(produtos);
		return produtos;
	}

	private void setBase64Images(List<Produto> produtos) {
		for (Produto produto2 : produtos) {
			String image = fileLoader.load(produto2.getSumarioPath());
			produto2.setImageFile(image);
		}
	}
}
