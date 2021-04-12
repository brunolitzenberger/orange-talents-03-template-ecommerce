package br.com.zupacademy.bruno.mercadolivre.controller;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.PlataformaPagamento;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestCompra;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.EmailSender;

@RestController
@RequestMapping
public class CompraController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EmailSender emailSender;
	
	@PostMapping("/produtos/{produtoId}/compra")
	@Transactional
	public String novaCompra(@PathVariable("produtoId") Long produtoId, @RequestBody @Valid RequestCompra request, Principal principal, UriComponentsBuilder uriComponentsBuilder){
		Produto produto = em.find(Produto.class, produtoId);
		if(produto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O produto que você quer comprar não existe");
		}
		Usuario comprador = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		produto.validaUsuario(comprador);
		Compra compra = request.toModel(produto, comprador);
		em.persist(compra);
		emailSender.sendEmailCompra(produto, comprador);
		if(request.getGateway().equals(PlataformaPagamento.PAGSEGURO)) {
			UriComponents urlPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro").buildAndExpand(compra.getId());
			return "www.pagseguro.com/" + compra.getId()  + "?redirectUrl=" + urlPagSeguro;
		}else {
			UriComponents urlPayPal = uriComponentsBuilder.path("/retorno-paypal").buildAndExpand(compra.getId());
			return "www.paypal.com/" + compra.getId()  + "?redirectUrl=" + urlPayPal;
		}
		
	}
	
	
}
