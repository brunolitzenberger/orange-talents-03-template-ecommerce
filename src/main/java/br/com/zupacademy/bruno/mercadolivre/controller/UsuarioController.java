package br.com.zupacademy.bruno.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestUsuario;

@RestController
@RequestMapping
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping("/usuario")
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid RequestUsuario request){
		Usuario usuario = request.toModel();
		em.persist(usuario);
		return ResponseEntity.ok().body("Usuário criado com sucesso.");
	}
	
}
