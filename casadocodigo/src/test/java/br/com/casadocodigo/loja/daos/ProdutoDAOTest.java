package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.infra.FileLoader;
import br.com.casadocodigo.loja.models.Categoria;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JPAConfiguration.class, ProdutoDAO.class, FileLoader.class, DataSourceConfigurationTest.class }) 
@ActiveProfiles("test")
@Transactional
public class ProdutoDAOTest {


    @Autowired
    private ProdutoDAO produtoDao;
    

    @Test
    public void deveSomarTodosOsPrecosPorTipoLivro() {

        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

        livrosImpressos.stream().forEach(produtoDao::gravar);
        livrosEbook.stream().forEach(produtoDao::gravar);

        BigDecimal valor = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
        Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
        
    }
    
    @Test
    public void mustListProdutos(){
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        livrosImpressos.stream().forEach(produtoDao::gravar);
        List<Produto> produtosRetornados = produtoDao.listar();
        Assert.assertEquals(livrosImpressos, produtosRetornados);
    }
    
    @Test
    public void mustLimitedListProdutos(){
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(2).buildAll();
        livrosImpressos.stream().forEach(produtoDao::gravar);
        List<Produto> produtosRetornados = produtoDao.listarLimitada(2);
//        livrosImpressos.forEach(livro ->  {
//			Optional<Produto> livroFiltrado = produtosRetornados.stream().filter(livroRetornado -> livroRetornado.getId() == livro.getId()).findFirst();
//			Assert.assertEquals(livro, livroFiltrado.get());
//		});
    }
    
    @Test
    public void findProduto(){
    	Produto produto = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).buildOne();
    	produtoDao.gravar(produto);
    	Produto produtoEncontrado = produtoDao.find(produto.getId());
    	Assert.assertEquals(produto, produtoEncontrado);
    }

    @Test
    public void deletarProduto(){
    	Produto produto = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).buildOne();
    	produtoDao.gravar(produto);
    	produtoDao.deletar(produto.getId());
		Produto produtoNaoEncontrado = null;
    	try {
    		produtoNaoEncontrado = produtoDao.find(produto.getId());
		} catch (Exception e) {
			Assert.assertNull(produtoNaoEncontrado);
		}
    }
    
    @Test
    public void atualizarProduto(){
    	Produto produto = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).buildOne();
    	String tituloAnterior = produto.getTitulo();
    	produtoDao.gravar(produto);
    	Produto produtoEncontrado = produtoDao.find(produto.getId());
    	produtoEncontrado.setTitulo("Produto Editado");
    	produtoDao.gravar(produtoEncontrado);
    	Assert.assertNotEquals(tituloAnterior, produtoEncontrado.getTitulo());
    }
    
    @Test
    public void listarProdutoPorCategoria(){
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        livrosImpressos.stream().forEach(produtoDao::gravar);
        List<Produto> livrosAgile = produtoDao.listarPorCategoria("AGILE");
        livrosAgile.forEach(livro -> {
        	Assert.assertEquals(livro.getCategorias().stream().filter(
        		categoria -> 
        			categoria.equals(Categoria.AGILE)).findFirst().get(), Categoria.AGILE);
        });
    	
    }
    
}
