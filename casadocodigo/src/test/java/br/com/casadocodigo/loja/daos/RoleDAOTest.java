package br.com.casadocodigo.loja.daos;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.exception.AppException;
import br.com.casadocodigo.loja.models.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class,DataSourceConfigurationTest.class, RoleDAO.class})
@ActiveProfiles("test")
@Transactional
public class RoleDAOTest {

	
	@Autowired
	private RoleDAO roleDAO;
	
	@Test
	public void findByName() throws AppException{
		createRole("ADMIN");
		createRole("USER");
		Role roleAdmin = roleDAO.findByName("ADMIN");
		Role roleUser = roleDAO.findByName("USER");
		assertEquals(roleAdmin.getNome(), "ADMIN");
		assertEquals(roleUser.getNome(), "USER");
	}
	
	private Role createRole(String roleName) {
		Role role = new Role();
		role.setNome(roleName);
		roleDAO.save(role);
		return role;
	}
}
