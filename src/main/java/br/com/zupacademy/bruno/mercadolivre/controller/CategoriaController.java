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

import br.com.zupacademy.bruno.mercadolivre.controller.model.Categoria;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestCategoria;

@RestController
@RequestMapping
public class CategoriaController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping("/categorias")
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid RequestCategoria request){
		Categoria categoria = request.toModel(em);
		em.persist(categoria);
		return ResponseEntity.ok().body("Categoria " + categoria.getNome() + " cadastrada com sucesso.");
		
	}
	
}
