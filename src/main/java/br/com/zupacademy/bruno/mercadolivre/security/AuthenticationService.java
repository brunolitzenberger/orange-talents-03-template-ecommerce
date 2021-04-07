package br.com.zupacademy.bruno.mercadolivre.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

@Service
public class AuthenticationService implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class).setParameter("login",login).getSingleResult();			
		return new DetalhesUsuarioService(usuario);
	}

}
