package br.com.casadocodigo.loja.conf;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.daos.RoleDAO;
import br.com.casadocodigo.loja.daos.UsuarioDAO;
import br.com.casadocodigo.loja.models.Categoria;
import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.models.Usuario;

@Component
public class CriadorDeProdutos {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JpaTransactionManager transactionManager;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@PostConstruct
	public void init() {
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				verifyRoles();
				verifyUsers();
				verifyProdutos();
			}

			private void verifyProdutos() {
				List<Produto> listaDeProdutos = produtoDAO.listar();
				
				if (listaDeProdutos.isEmpty()) {
					criaProdutos();
				}
			}

			private void verifyUsers() {
				UserDetails usuario = null;
				try {
					usuarioDAO.loadUserByUsername("admin@casadocodigo.com.br");
				} catch (Exception e) {
					/**
					 * Creating admin user;
					 */
					Usuario adminUser = new Usuario();
					adminUser.setEmail("admin@casadocodigo.com.br");
					adminUser.setNome("Administrador");
					adminUser.setSenha("$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq"); //123456
					
					Role role = new Role();
					role.setNome("ROLE_ADMIN");
					
					adminUser.setRoles(Arrays.asList(role));
					em.persist(adminUser);
				}
				
			}

			private void verifyRoles() {
				List<Role> adminRole = roleDAO.findByName("ROLE_ADMIN");
				
				if (adminRole.isEmpty()) {
					Role roleAdmin = new Role();
					roleAdmin.setNome("ROLE_ADMIN");
					
					em.persist(roleAdmin);
				}
				
				List<Role> clientRole = roleDAO.findByName("ROLE_CLIENT");
				
				if (clientRole.isEmpty()) {
					Role roleClient = new Role();
					roleClient.setNome("ROLE_CLIENT");
					em.persist(clientRole);
				}
			}
			
			private void criaProdutos() {
				Produto algoritmosEmJava = new Produto();
				algoritmosEmJava.setTitulo("Algoritmos em Java");
				algoritmosEmJava.setDescricao(" Em nosso dia a dia, realizamos uma série de buscas e ordenações que nos são tão naturais que nem percebemos como sua "
						+ "presença é ubíqua e facilita nossa vida. Quando pesquisamos produtos por preço em uma loja, ou queremos buscar uma pessoa em uma "
						+ "lista, ou mesmo quando organizamos as cartas de baralho para algum jogo, estamos usando algoritmos."
						+ "Neste livro, Guilherme Silveira mostra que, na computação, não é diferente. Muitos dos problemas complexos de lógica com que "
						+ "desenvolvedores lidam todos os dias envolvem conceitos básicos de busca e ordenação, alguns dos quais priorizam economia de tempo "
						+ "ou de memória. Você verá como funcionam os algoritmos e como implementá-los, por meio do estudo de soluções que já usamos no cotidiano. "
						+ "Entendê-los a ponto de sermos capazes de recriá-los nos traz um conhecimento valioso para todo programador: o pensamento lógico e a "
						+ "quebra de problemas em partes menores que podem ser resolvidas com algoritmos. ");
				
				algoritmosEmJava.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/eBook-Algoritmos_large.jpg?v=1484936519");
				setPrecosOnProdutos(algoritmosEmJava);
				algoritmosEmJava.setDataLancamento(Calendar.getInstance());
				algoritmosEmJava.setPaginas(440);
				algoritmosEmJava.setCategorias(Arrays.asList(Categoria.JAVA));
				
				em.persist(algoritmosEmJava);
				
				Produto jpaEficaz = new Produto();
				jpaEficaz.setTitulo("JPA Eficaz");
				jpaEficaz.setDescricao("  Entender o básico da JPA pode ser simples e rápido, mas com o uso do dia a dia, ela se torna uma ferramenta que demanda muito cuidado. "
						+ "É preciso conhecer bem seus detalhes para não cair em armadilhas."
						+ "Nesse livro, Hebert Coelho explica em tópicos curtos, e direto ao ponto, técnicas para usar a JPA da melhor forma possível, com atenção aos equívocos "
						+ "mais comuns. Você vai aprender a evitar o problema de N+1 consultas, usar corretamente o controle transacional, fazer consultas que envolvam relacionamentos, "
						+ "controlar quando usar EAGER ou LAZY nos relacionamentos, evitar a famigerada LazyInitializationException e muito mais.");
				
				jpaEficaz.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/jpa-eficaz-featured_large.png?v=1411490333");
				setPrecosOnProdutos(jpaEficaz);
				jpaEficaz.setDataLancamento(Calendar.getInstance());
				jpaEficaz.setPaginas(167);
				jpaEficaz.setCategorias(Arrays.asList(Categoria.JAVA));
				
				em.persist(jpaEficaz);
				
				Produto jquery = new Produto();
				jquery.setTitulo("Dominando JavaScript com jQuery");
				jquery.setDescricao(" Esqueça os livros que mais parecem uma documentação da API! Aqui você encontra jQuery apresentado de maneira incremental, substituindo código JavaScript"
						+ " longo e complicado.Truques de animação, componentes visuais, e manipulação do DOM através de jQuery, jQueryUI e jQuery mobile. Refatore uma aplicação JavaScript e "
						+ "finalmente entenda callbacks, seletores e AJAX sem ficar preso ao copy and paste. Tudo isso com a versão 2.x, a mais recente do framework.");
				
				jquery.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/javascript-jquery-featured_large.png?v=1416319134");
				setPrecosOnProdutos(jquery);
				jquery.setDataLancamento(Calendar.getInstance());
				jquery.setPaginas(193);
				jquery.setCategorias(Arrays.asList(Categoria.FRONT_END));
				
				em.persist(jquery);
				
				Produto css = new Produto();
				css.setTitulo("CSS Eficiente");
				css.setDescricao(" Quando aprendemos a trabalhar com CSS, frequentemente nos pegamos perdidos em detalhes fundamentais que não nos são explicados. "
						+ "Por vezes, alguns desses detalhes passam despercebidos até pelo desenvolvedor front-end mais experiente. Mas como ir além do conhecimento básico do CSS e preparar o caminho"
						+ " para explorar tópicos mais avançados?Neste livro, Tárcio Zemel ensina como organizar seu estilo, entender especificidade, como usar diferentes seletores, trabalhar orientado a "
						+ " objetos com CSS e várias outras técnicas que farão diferença no dia a dia do trabalho com os estilos e abrirão novas possibilidades para você explorar ainda mais o CSS. ");
				
				css.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145");
				setPrecosOnProdutos(css);
				css.setDataLancamento(Calendar.getInstance());
				css.setPaginas(131);
				css.setCategorias(Arrays.asList(Categoria.FRONT_END));
				
				em.persist(css);
				
				Produto spring = new Produto();
				spring.setTitulo("Vire o jogo com Spring Framework ");
				spring.setDescricao(" Criado para simplificar o desenvolvimento de aplicações Java, o Spring se tornou um dos frameworks de mais destaque dentro desse grande ambiente."
						+ "Aprenda muito mais que o básico do Spring, desde o tradicional Container de Inversão de Controle e Injeção de Dependências, passando pelos robustos módulos de segurança,"
						+ " transações, programação orientada a aspectos e também o fantástico módulo MVC, o SpringMVC.");
				
				spring.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/spring-framework-featured_large.png?v=1411567960");
				setPrecosOnProdutos(spring);
				spring.setDataLancamento(Calendar.getInstance());
				spring.setPaginas(296);
				spring.setCategorias(Arrays.asList(Categoria.JAVA));
				
				em.persist(spring);
			}
			

			private void setPrecosOnProdutos(Produto produto) {
				Preco precoEbook = new Preco();
				precoEbook.setPreco(TipoPreco.EBOOK);
				precoEbook.setValor(new BigDecimal(30));
				
				Preco precoImpresso = new Preco();
				precoImpresso.setPreco(TipoPreco.IMPRESSO);
				precoImpresso.setValor(new BigDecimal(50));
				
				Preco precoCombo = new Preco();
				precoCombo.setPreco(TipoPreco.COMBO);
				precoCombo.setValor(new BigDecimal(70));
				
				produto.setPrecos(Arrays.asList(precoEbook, precoImpresso, precoCombo));
			}
		});		
	}
	
}
