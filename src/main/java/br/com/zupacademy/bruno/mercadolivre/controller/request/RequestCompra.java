package br.com.zupacademy.bruno.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.PlataformaPagamento;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;

public class RequestCompra {
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	private PlataformaPagamento gateway;

	
	public RequestCompra(@NotBlank PlataformaPagamento gateway, Integer quantidade) {
		this.gateway = gateway;
		this.quantidade = quantidade;
	}
	
	public Compra toModel(Produto produto, Usuario comprador) {
		produto.abaterEstoque(quantidade);
		return new Compra(produto, quantidade, comprador, produto.getValor());
	}

	public PlataformaPagamento getGateway() {
		return this.gateway;
	}
	
	
}
