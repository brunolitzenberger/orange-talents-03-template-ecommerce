package br.com.zupacademy.bruno.mercadolivre.controller.utils;

import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

@Component
public class EmailSender {
	
	public String sendEmailPergunta(Produto produto, Usuario usuario) {
		String destinatario = produto.getUsuario().getUsername();
		String remetente = usuario.getUsername();
		String mensagem = "Enviaram uma nova pergunta sobre seu produto " + produto.getNome();
		String enviado = "Sua pergunta sobre o produto " + produto.getNome() + " foi enviada com sucesso.";
		return enviado;
	}
	
	public String sendEmailCompra(Produto produto, Usuario usuario) {
		String destinatario = produto.getUsuario().getUsername();
		String remetente = usuario.getUsername();
		String mensagem = "Iniciaram a compra do seu produto: " + produto.getNome();
		return mensagem;
	}

	public void sendEmailStatus(StatusPagamento status, Compra compra) {
		if(status.equals(StatusPagamento.SUCESSO)) {
			sendEmailSucesso(compra);
		}else {
			sendEmailFalha(compra);
		}
	}

	private String sendEmailFalha(Compra compra) {
		return compra.falhaCompra();		
	}

	private String sendEmailSucesso(Compra compra) {
		return compra.sucessoCompra();
	}

}
