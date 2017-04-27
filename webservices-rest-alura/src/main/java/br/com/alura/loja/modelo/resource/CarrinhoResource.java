package br.com.alura.loja.modelo.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {

	private static CarrinhoDAO carrinhoDao;
	private static XStream xtream;

	public CarrinhoResource() {
		carrinhoDao = new CarrinhoDAO();
		xtream = new XStream();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String busca(@PathParam("id")long id){
		Carrinho carrinho = carrinhoDao.busca(id);
		return carrinho.toJSON();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo){
		Carrinho carrinho = (Carrinho) xtream.fromXML(conteudo);
		carrinhoDao.adiciona(carrinho);
		URI uri = URI.create("/carrinhos/"+carrinho.getId());
		return Response.created(uri).build();
	}
}
