package br.com.alura.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest {

	private static HttpServer server;
	private static WebTarget target;

	@BeforeClass
	public static void initServer(){
		server = Servidor.inicializaServidor();
		Client client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080");
	}
	
	@AfterClass
	public static void destroy(){
		server.stop();
	}
	
	@Test
	public void testaXmlCliente(){
		String projetoString = target.path("/projetos/1").request().get(String.class);
		Projeto projeto = (Projeto) new XStream().fromXML(projetoString);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void TestaAdiciona(){
		String carrinhoXml = constroiCarrinho();
		Entity<String> entity = Entity.entity(carrinhoXml, MediaType.APPLICATION_XML);
        Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
	}

	private String constroiCarrinho() {
		Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        return carrinho.toXML();
	}
}
