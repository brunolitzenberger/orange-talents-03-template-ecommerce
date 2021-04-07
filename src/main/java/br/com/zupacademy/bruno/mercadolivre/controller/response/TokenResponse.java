package br.com.zupacademy.bruno.mercadolivre.controller.response;

public class TokenResponse {

	private String token;
	private String tipo;
	
	public TokenResponse(String token) {
		this.token = token;
		this.tipo = "Bearer";
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
	
}
