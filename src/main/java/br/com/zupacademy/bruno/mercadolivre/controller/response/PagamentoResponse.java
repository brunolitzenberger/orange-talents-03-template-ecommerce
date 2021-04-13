package br.com.zupacademy.bruno.mercadolivre.controller.response;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Transacao;

public interface PagamentoResponse {

	Transacao toModel(Compra compra);
	
	
}
