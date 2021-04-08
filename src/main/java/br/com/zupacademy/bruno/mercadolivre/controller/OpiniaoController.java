package br.com.zupacademy.bruno.mercadolivre.controller;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.bruno.mercadolivre.controller.model.OpiniaoProduto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestOpiniao;

@RestController
@RequestMapping
public class OpiniaoController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping("/{produtoId}/opiniao")
	@Transactional
	public ResponseEntity<?> opiniao(@PathVariable(name = "produtoId") Long produtoId, @RequestBody @Valid RequestOpiniao request, Principal principal){
		Produto produto = em.find(Produto.class, produtoId);
		if(produto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto ao qual você quer opinar não existe");
		}
		Usuario usuario = em.createQuery("SELECT u FROM Usuario u where u.login = :login", Usuario.class).setParameter("login", principal.getName()).getSingleResult();
		boolean mesmoUsuario = produto.verificaUsuario(usuario);
		if(mesmoUsuario) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode opinar seu próprio produto.");
		}
		
		OpiniaoProduto opiniao = request.toModel(usuario, produto);
		produto.adicionaOpiniao(opiniao);
		em.merge(produto);
		return ResponseEntity.ok().body(produto.toString());
	}
	
}
