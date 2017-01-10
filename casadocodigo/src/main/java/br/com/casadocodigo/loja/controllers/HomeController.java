package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/")
	public ModelAndView index(){
		List<Produto> produtos = produtoDao.listar();
		ModelAndView model = new ModelAndView("home");
		model.addObject("produtos", produtos);
		return model;
	}

}
