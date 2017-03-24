package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.infra.FileLoader;
import br.com.casadocodigo.loja.models.Categoria;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

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
		List<Produto> produtos = manager.createQuery("select p from Produto p inner join fetch p.categorias", Produto.class).getResultList();
//		List<Produto> produtos = manager.createNativeQuery("SELECT * FROM .Produto p INNER JOIN produto_categoria c on  p.id = categoria_id INNER JOIN Produto_precos o on p.id = o.Produto_id;", Produto.class).getResultList();
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
	

	public List<Produto> listarPorCategoria(String categoria) {
		List<Produto> produtos = manager.createQuery("select p from Produto p join fetch p.categorias categorias where categorias = :categoria", Produto.class)
				.setParameter("categoria", Categoria.valueOf(categoria)).getResultList();
		setBase64Images(produtos);
		return produtos;
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
		return manager.createQuery("select sum(preco.valor) from Produto p inner join p.precos preco where preco.preco = :tipoPreco", BigDecimal.class)
				.setParameter("tipoPreco", tipoPreco)
				.getSingleResult();
	}

	private void setBase64Images(List<Produto> produtos) {
		if (!produtos.isEmpty()) {
			for (Produto produto2 : produtos) {
				String image = fileLoader.load(produto2.getSumarioPath());
				produto2.setImageFile(image);
			}
		}
	}
}
