package br.com.alura.test;

import static org.junit.Assert.assertEquals;

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

import com.google.gson.Gson;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Projeto;

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
		Projeto projeto = new Gson().fromJson(projetoString, Projeto.class);
		assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void testaAdiciona(){
		Projeto projeto = constroiProduto();
		Entity<String> projetoXml = Entity.entity(projeto.toXML(), MediaType.APPLICATION_XML);
		Response response = target.path("/projetos").request().post(projetoXml);
		String projetoInserido = target.path(response.getHeaderString("Location").replaceFirst("http://localhost:8080", ""))
				.request().get(String.class);
		Projeto projetoRetornado = new Gson().fromJson(projetoInserido, Projeto.class);
		assertEquals(201, response.getStatus());
		assertEquals(projetoRetornado.getNome(), projeto.getNome());
	}

	private Projeto constroiProduto() {
		return new Projeto(2l, "Projeto de Java", 2017);
	}
	
}
