package br.com.zupacademy.bruno.mercadolivre.controller.response;

import br.com.zupacademy.bruno.mercadolivre.controller.model.OpiniaoProduto;

public class ResponseOpiniao {

	private Integer nota;
	private String titulo;
	private String descricao;
	private String usuario;

	public ResponseOpiniao(OpiniaoProduto opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.usuario = opiniao.getUsuario().getUsername();
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUsuario() {
		return usuario;
	}
	
	
	
	
}

