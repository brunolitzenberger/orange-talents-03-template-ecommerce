package br.com.zupacademy.bruno.mercadolivre.controller.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	private String login;
	private String senha;	
	
	public LoginRequest(String email, String senha) {
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
