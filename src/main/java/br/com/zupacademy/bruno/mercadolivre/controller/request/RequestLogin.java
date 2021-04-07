package br.com.zupacademy.bruno.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class RequestLogin {
	@NotBlank
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;

	public RequestLogin(@NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		this.login = email;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}

}
