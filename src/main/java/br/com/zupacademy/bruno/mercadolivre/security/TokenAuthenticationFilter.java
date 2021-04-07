package br.com.zupacademy.bruno.mercadolivre.security;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private JWTService jwtService;
	private EntityManager em;

	public TokenAuthenticationFilter(JWTService jwtService, EntityManager em) {
		this.jwtService = jwtService;
		this.em = em;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		Boolean tokenValido = jwtService.tokenValido(token);
		if (tokenValido) {
			autenticarUsuario(token);
		}
		filterChain.doFilter(request, response);

	}

	private void autenticarUsuario(String token) {
		Usuario usuario = em.find(Usuario.class, jwtService.getUsuarioId(token));
		DetalhesUsuarioService userDS = new DetalhesUsuarioService(usuario);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDS, null,
				userDS.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
