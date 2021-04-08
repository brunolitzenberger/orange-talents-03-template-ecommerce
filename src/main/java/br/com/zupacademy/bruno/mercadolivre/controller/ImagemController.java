package br.com.zupacademy.bruno.mercadolivre.controller;

import java.security.Principal;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestImagem;
import br.com.zupacademy.bruno.mercadolivre.validator.ProibeImagensIguaisValidator;

@RestController
@RequestMapping
public class ImagemController {
	
	@PersistenceContext
	private EntityManager em;
	
	private SimuladorDeUpload simuladorDeUpload;
	
	public ImagemController(SimuladorDeUpload simuladorDeUpload) {
		this.simuladorDeUpload = simuladorDeUpload;
	}
	
	@InitBinder
	private void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeImagensIguaisValidator());
	}
	

	@PostMapping("/{produtoId}/imagens")
	@Transactional
	public ResponseEntity<?> adicionarImagem(@PathVariable(name = "produtoId") Long produtoId, @Valid RequestImagem request, Principal principal){
		
		Produto produto = em.find(Produto.class, produtoId);
		if(produto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O produto ao qual você quer adicionar imagens não existe");
		}

		Usuario usuario = em.createQuery("SELECT u FROM Usuario u where u.login = :login", Usuario.class).setParameter("login", principal.getName()).getSingleResult();
		boolean mesmoUsuario = produto.verificaUsuario(usuario);
		if(!mesmoUsuario) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode salvar imagens à um produto que não pertence a você.");
		}
		
		Set<String> links = simuladorDeUpload.enviar(request.getImagens(), produto);
		produto.addImagem(links);
		em.merge(produto);
		return ResponseEntity.ok().body(produto.toString());
	}
	
}
