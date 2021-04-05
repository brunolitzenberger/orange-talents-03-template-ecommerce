package br.com.zupacademy.bruno.mercadolivre.controller.utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaValida {
	
	private String senha;

	public SenhaValida(@NotBlank @Size(min= 6) String senha) {
		this.senha = senha;
	}
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
	

}
