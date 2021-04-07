package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import br.com.zupacademy.bruno.mercadolivre.controller.utils.SenhaValida;

@Entity
public class Usuario {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;
	
	private String senha;
	
	private LocalDateTime instante;
	
	@Deprecated
	public Usuario() {
		
	}

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


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", instante=" + instante + "]";
	}

	
	

}
