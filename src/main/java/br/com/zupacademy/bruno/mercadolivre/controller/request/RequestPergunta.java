package br.com.zupacademy.bruno.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.mercadolivre.controller.model.PerguntaProduto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

public class RequestPergunta {

	@NotBlank
	private String titulo;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public PerguntaProduto toModel(Usuario usuario, Produto produto) {
		return new PerguntaProduto(titulo, usuario, produto);
	}

	@Override
	public String toString() {
		return "RequestPergunta [titulo=" + titulo + "]";
	}

}
