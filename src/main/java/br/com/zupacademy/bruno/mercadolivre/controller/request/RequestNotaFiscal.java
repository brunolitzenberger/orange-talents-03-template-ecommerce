package br.com.zupacademy.bruno.mercadolivre.controller.request;

import javax.validation.constraints.NotNull;

public class RequestNotaFiscal {
	@NotNull
	private Long idCompra;
	@NotNull	
	private Long idComprador;
	
	public RequestNotaFiscal(@NotNull Long idCompra, @NotNull Long idComprador) {
		super();
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return "NotaFiscalRequest [idCompra=" + idCompra + ", idComprador=" + idComprador + "]";
	}
	
	
}
