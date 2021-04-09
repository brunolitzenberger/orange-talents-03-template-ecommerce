package br.com.zupacademy.bruno.mercadolivre.controller;

import java.security.Principal;

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

import br.com.zupacademy.bruno.mercadolivre.controller.model.PerguntaProduto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestPergunta;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.EmailSender;

@RestController
@RequestMapping
public class PerguntaController {

	@PersistenceContext
	private EntityManager em;
	private EmailSender sender;

	public PerguntaController(EmailSender sender) {
		this.sender = sender;
	}
	

	@PostMapping("/{produtoId}/pergunta")
	@Transactional
	public ResponseEntity<?> opiniao(@PathVariable(name = "produtoId") Long produtoId,
			@RequestBody @Valid RequestPergunta request, Principal principal) {
		Produto produto = em.find(Produto.class, produtoId);
		if (produto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Produto sobre o qual você quer perguntar não existe");
		}
		Usuario usuario = em.createQuery("SELECT u FROM Usuario u where u.login = :login", Usuario.class)
				.setParameter("login", principal.getName()).getSingleResult();
		if(usuario.equals(produto.getUsuario())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode perguntar sobre seu próprio produto.");
		}
		PerguntaProduto pergunta = request.toModel(usuario, produto);
		produto.adicionaPergunta(pergunta);
		em.merge(produto);
		String sucesso = sender.sendEmail(produto, usuario);
		return ResponseEntity.ok().body(sucesso);
	}

}
