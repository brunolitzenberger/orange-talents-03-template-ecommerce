package br.com.zupacademy.bruno.mercadolivre.controller.response;

import br.com.zupacademy.bruno.mercadolivre.controller.model.ProdutoCaracteristica;

public class ResponseCaracteristica {

	private String nome;
	private String descricao;

	public ResponseCaracteristica(ProdutoCaracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
