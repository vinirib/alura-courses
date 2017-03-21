package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.exception.AppException;
import br.com.casadocodigo.loja.models.Role;

@Repository
@Transactional
public class RoleDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Role findByName(String roleName) throws AppException{
		Role role = new Role();
		try {
			role = manager.createQuery("select r from Role r where r.nome = :roleName", Role.class)
				.setParameter("roleName", roleName)
				.getSingleResult();
		} catch (Exception e) {
			throw new AppException("Could not find " +roleName+ " role");
		}
		return role;
	}
	
	public void save(Role role){
		manager.persist(role);
	}

}
