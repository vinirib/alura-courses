package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Role;

@Repository
@Transactional
public class RoleDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Role> findByName(String roleName){
		return manager.createQuery("select r from Role r", Role.class)
			.getResultList();
	}

}
