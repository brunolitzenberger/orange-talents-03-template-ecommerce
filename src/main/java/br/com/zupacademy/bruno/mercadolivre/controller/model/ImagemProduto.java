package br.com.zupacademy.bruno.mercadolivre.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@Valid
	private Produto produto;
	@URL
	@NotBlank
	private String link;
	
	@Deprecated
	public ImagemProduto() {
		
	}
	
	public ImagemProduto(@NotNull @Valid Produto produto, @NotBlank String link) {
		this.produto = produto;
		this.link = link;
	}

	@Override
	public String toString() {
		return "ImagemProduto [id=" + id + ", link=" + link + "]";
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public String getLink() {
		return link;
	}

	
	
	
}
