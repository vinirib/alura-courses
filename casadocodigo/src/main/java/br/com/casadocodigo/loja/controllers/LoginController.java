package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
public class LoginController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public ModelAndView logout(){
    	return new ModelAndView("redirect:/produtos");
    }
    
    @RequestMapping(value="/login/criar", method=RequestMethod.GET)
    public String criar(Usuario usuario){
    	return "formLogin";
    }
    
    @RequestMapping(value="/login/gravar",method=RequestMethod.POST)
    public String gravar(Usuario usuario, RedirectAttributes redirectAttributes){
    	usuarioDAO.save(usuario);
    	redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
    	return "redirect:/login";
    }
}
