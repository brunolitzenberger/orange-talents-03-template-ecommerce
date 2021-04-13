package br.com.zupacademy.bruno.mercadolivre.controller.response;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Transacao;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusPagamento;

public class PayPalResponse implements PagamentoResponse{

	@NotBlank
	private String idTransacao;
	private int status;
	
	public PayPalResponse(String idTransacao, int status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	
	public Transacao toModel(Compra compra) {
		StatusPagamento statusConvertido = status == 0 ? StatusPagamento.FALHA : StatusPagamento.SUCESSO;
		return new Transacao(compra, idTransacao, statusConvertido);
	}


	@Override
	public String toString() {
		return "PagSeguroResponse [idTransacao=" + idTransacao + ", status=" + status + "]";
	}
	
	
}
