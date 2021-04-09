package br.com.zupacademy.bruno.mercadolivre.controller;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestProduto;
import br.com.zupacademy.bruno.mercadolivre.controller.response.ResponseProduto;
import br.com.zupacademy.bruno.mercadolivre.validator.ProibeCaracteristicaComNomeIgualValidator;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ResponseProduto> create(@RequestBody @Valid RequestProduto request, Principal principal){
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Produto produto = request.toModel(em, usuario);
		em.persist(produto);
		return ResponseEntity.ok().body(new ResponseProduto(produto));
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<ResponseProduto> produto(@PathVariable Long id){
		Produto produto = em.find(Produto.class, id);
		ResponseProduto response = ResponseProduto.converter(produto);
		return ResponseEntity.ok().body(response);
		
	}
	
}
