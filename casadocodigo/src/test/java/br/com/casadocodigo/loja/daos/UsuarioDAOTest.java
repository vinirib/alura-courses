package br.com.casadocodigo.loja.daos;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JPAConfiguration.class, UsuarioDAO.class, RoleDAO.class, DataSourceConfigurationTest.class })
@ActiveProfiles("test")
@Transactional
public class UsuarioDAOTest {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Test
	public void saveLogin(){
		Usuario usuario = createUsuario();
		usuarioDAO.save(usuario);
		UserDetails usuarioSaved = usuarioDAO.loadUserByUsername(usuario.getEmail());
		assertEquals(usuarioSaved,(Usuario) usuario);
	}
	
	@Test(expected=RuntimeException.class)
	public void emptyUsuario(){
		usuarioDAO.loadUserByUsername("something@org.com");
	}

	private Usuario createUsuario() {
		Role role = createRole();
		Usuario usuario = new Usuario();
		usuario.setNome("Vinicius");
		usuario.setSenha("123456");
		usuario.setRoles(Lists.newArrayList(role));
		usuario.setEmail("vinicius@teste.com.br");
		return usuario;
	}

	private Role createRole() {
		Role role = new Role();
		role.setNome("ADMIN");
		roleDAO.save(role);
		return role;
	}
}
