package br.com.zupacademy.bruno.mercadolivre.controller.utils;

import org.springframework.stereotype.Component;

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

}
