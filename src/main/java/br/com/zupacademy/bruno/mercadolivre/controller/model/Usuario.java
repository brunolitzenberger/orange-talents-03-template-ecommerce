package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import br.com.zupacademy.bruno.mercadolivre.controller.utils.SenhaValida;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String login;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private LocalDateTime instante;

	public Usuario(String login, @Valid SenhaValida senha, LocalDateTime instante) {
		this.login = login;
		this.senha = senha.hash();
		this.instante = instante;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
	
	

}
