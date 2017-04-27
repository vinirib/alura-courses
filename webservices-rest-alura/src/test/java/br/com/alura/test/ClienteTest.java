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
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {
	
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
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = new Gson().fromJson(conteudo, Carrinho.class);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	
	}
	@Test
	public void TestaAdiciona(){
		Carrinho carrinho = constroiCarrinho();
		Entity<String> entity = Entity.entity(carrinho.toXML(), MediaType.APPLICATION_XML);
        Response response = target.path("/carrinhos").request().post(entity);
        String carrinhoJson = target.path(response.getHeaderString("Location").replaceFirst("http://localhost:8080", ""))
        		.request().get(String.class);
        Carrinho carrinhoAdicionado = new Gson().fromJson(carrinhoJson, Carrinho.class);
        assertEquals(201, response.getStatus());
        assertEquals(carrinhoAdicionado.getRua(), carrinho.getRua());
	}

	private Carrinho constroiCarrinho() {
		Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        return carrinho;
	}

}
