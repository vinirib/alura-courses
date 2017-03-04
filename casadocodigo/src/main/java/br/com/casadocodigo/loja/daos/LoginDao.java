package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class LoginDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Usuario usuario) {
		encryptPassword(usuario);
		manager.persist(usuario);
	}

	private void encryptPassword(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String cryptedPassword = encoder.encode(usuario.getSenha());
		usuario.setSenha(cryptedPassword);
	}
}
