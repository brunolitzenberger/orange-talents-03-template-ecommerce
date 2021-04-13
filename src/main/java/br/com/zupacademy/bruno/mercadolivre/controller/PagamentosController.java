package br.com.zupacademy.bruno.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.NotaFiscal;
import br.com.zupacademy.bruno.mercadolivre.controller.response.PagSeguroResponse;
import br.com.zupacademy.bruno.mercadolivre.controller.response.PagamentoResponse;
import br.com.zupacademy.bruno.mercadolivre.controller.response.PayPalResponse;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.EmailSender;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusPagamento;

@RestController
@RequestMapping
public class PagamentosController {

	@PersistenceContext
	private EntityManager em;
	
	private EmailSender emailSender;
	private NotaFiscal nf;
	private Ranking ranking;
	
	public PagamentosController(EmailSender emailSender, NotaFiscal nf, Ranking ranking) {
		this.emailSender = emailSender;
		this.nf = nf;
		this.ranking = ranking;
	}
	
	@PostMapping("/compra-pagseguro/{compraId}")
	@Transactional
	public ResponseEntity<StatusPagamento> fechamentoPagSeguro(@PathVariable("compraId") Long id, @Valid PagSeguroResponse request){
		return processaPagamentos(id, request);
	}
	
	
	@PostMapping("/retorno-paypal/{compraId}")
	@Transactional
	public ResponseEntity<StatusPagamento> fechamentoPagSeguro(@PathVariable("compraId") Long id, @Valid PayPalResponse request){
		return processaPagamentos(id, request);
	}
	
	private ResponseEntity<StatusPagamento> processaPagamentos(Long idCompra, PagamentoResponse request) {
		Compra compra = em.find(Compra.class, idCompra);
		StatusPagamento status = compra.adicionaTransacao(request);	
		em.merge(compra);	
		if(compra.compraComSucesso()) {
			nf.gera(compra);
			ranking.gera(compra);
		}

		emailSender.sendEmailStatus(status, compra);
		return ResponseEntity.ok().body(status);		
	}
	
}
