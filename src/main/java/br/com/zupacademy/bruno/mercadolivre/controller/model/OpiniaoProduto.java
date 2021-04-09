package br.com.zupacademy.bruno.mercadolivre.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Positive
	@Max(value = 5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	@OneToOne
	@NotNull
	private Usuario usuario;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	@Deprecated
	public OpiniaoProduto() {
		
	}

	public OpiniaoProduto(@NotNull @Positive @Max(value = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}



	@Override
	public String toString() {
		return "OpiniaoProduto [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario="
				+ usuario + "]";
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}


	
	
	
	
	
}
