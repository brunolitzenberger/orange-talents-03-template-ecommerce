package br.com.zupacademy.bruno.mercadolivre.controller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@ManyToOne
	private Categoria categoria;
	
	@Deprecated
	public Categoria() {
		
	}

	public Categoria(String nome) {
		this.nome = nome;
	}

	
	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoria=" + categoria + "]";
	}
	
	
	
	
	
}
