package br.com.zupacademy.bruno.mercadolivre.controller.response;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Transacao;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusPagamentoPagSeguro;

public class PagSeguroResponse implements PagamentoResponse{

	@NotBlank
	private String idTransacao;
	private StatusPagamentoPagSeguro status;
	
	public PagSeguroResponse(String idTransacao, StatusPagamentoPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	
	public Transacao toModel(Compra compra) {
		return new Transacao(compra, idTransacao, status.converter());
	}


	@Override
	public String toString() {
		return "PagSeguroResponse [idTransacao=" + idTransacao + ", status=" + status + "]";
	}
	
	
}
