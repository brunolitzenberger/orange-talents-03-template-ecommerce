package br.com.zupacademy.bruno.mercadolivre.controller;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestProduto;
import br.com.zupacademy.bruno.mercadolivre.validator.ProibeCaracteristicaComNomeIgualValidator;

@RestController
@RequestMapping
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@PostMapping("/produtos")
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid RequestProduto request, Principal principal){
		Usuario user = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class).setParameter("login",principal.getName()).getSingleResult(); 
		Produto produto = request.toModel(em, user);
		em.persist(produto);
		return ResponseEntity.ok().body(produto.toString());
	}
	
}
