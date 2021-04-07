package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import br.com.zupacademy.bruno.mercadolivre.controller.utils.SenhaValida;

@Entity
public class Usuario {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String login;
	@NotBlank
	@Column(nullable = false)
	private String senha;
	
	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now();
	
	@Deprecated
	public Usuario() {
		
	}

	public Usuario(@NotBlank String login, @Valid SenhaValida senha) {
		this.login = login;
		this.senha = senha.hash();
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


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", instante=" + instante + "]";
	}

	
	

}
