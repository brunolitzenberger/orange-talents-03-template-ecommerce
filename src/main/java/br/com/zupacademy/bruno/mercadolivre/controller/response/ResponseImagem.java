package br.com.zupacademy.bruno.mercadolivre.controller.response;

import br.com.zupacademy.bruno.mercadolivre.controller.model.ImagemProduto;

public class ResponseImagem {

	private String link;
	
	@Deprecated
	public ResponseImagem() {
		
	}

	public ResponseImagem(ImagemProduto imagem) {
		this.link = imagem.getLink();
	}

	public String getLink() {
		return link;
	}
}
