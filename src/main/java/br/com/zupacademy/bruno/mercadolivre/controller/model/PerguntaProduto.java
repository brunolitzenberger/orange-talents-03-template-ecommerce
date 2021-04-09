package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class PerguntaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now();

	@NotNull(message = "O produto que você quer perguntar não existe.")
	@ManyToOne
	private Produto produto;

	@Deprecated
	public PerguntaProduto() {

	}

	public PerguntaProduto(@NotBlank String titulo, Usuario usuario, Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}
	

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", usuario=" + usuario + ", instante=" + instante
				+ "]";
	}

	

}
