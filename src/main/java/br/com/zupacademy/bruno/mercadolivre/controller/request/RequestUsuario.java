package br.com.zupacademy.bruno.mercadolivre.controller.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.SenhaValida;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.annotations.UniqueValue;

public class RequestUsuario {
	
	@NotBlank
	@Email 
	@UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "Já existe um usuário com este login cadastrado no sistema.")
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;
	
	@NotNull
	@PastOrPresent
	private LocalDateTime instante;
	
	public RequestUsuario(String login, String senha, LocalDateTime instante) {
		this.login = login;
		this.senha = senha;
		this.instante = instante;
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
	
	public Usuario toModel() {
		return new Usuario(login, new SenhaValida(senha), instante);
	}
	
	

}
