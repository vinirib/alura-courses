package br.com.casadocodigo.loja.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casadocodigo.loja.daos.RoleDAO;
import br.com.casadocodigo.loja.models.Role;

public class TesteCriaRole {
	
	@Autowired
	private static RoleDAO roleDAO;

	public static void main(String[] args) {
		List<Role> role = roleDAO.findByName("ROLE_ADMIN");
		if (role.isEmpty()) {
			System.out.println("vazio");
		}

	}

}
