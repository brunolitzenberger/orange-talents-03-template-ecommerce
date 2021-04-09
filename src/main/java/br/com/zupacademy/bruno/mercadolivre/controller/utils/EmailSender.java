package br.com.zupacademy.bruno.mercadolivre.controller.utils;

import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

@Component
public class EmailSender {
	
	public String sendEmail(Produto produto, Usuario usuario) {
		String destinatario = produto.getUsuario().getLogin();
		String remetente = usuario.getLogin();
		String mensagem = "Enviaram uma nova pergunta sobre seu produto " + produto.getNome();
		String enviado = "Sua pergunta sobre o produto " + produto.getNome() + " foi enviada com sucesso.";
		return enviado;
	}

}
