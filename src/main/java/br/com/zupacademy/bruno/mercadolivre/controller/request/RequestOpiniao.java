package br.com.zupacademy.bruno.mercadolivre.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zupacademy.bruno.mercadolivre.controller.model.OpiniaoProduto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

public class RequestOpiniao {

	@NotNull
	@Positive
	@Max(value = 5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;


	public RequestOpiniao(@NotNull @Positive @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "RequestOpiniao [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

	public OpiniaoProduto toModel(Usuario usuario, Produto produto) {
		return new OpiniaoProduto(nota, titulo, descricao, usuario, produto);
		
	}
	
	

}
