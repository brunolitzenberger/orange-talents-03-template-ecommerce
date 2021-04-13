package br.com.zupacademy.bruno.mercadolivre.controller.utils;

import br.com.zupacademy.bruno.mercadolivre.controller.response.PagamentoResponse;

public interface Pagamento {

	PagamentoResponse pagamento(Long idCompra, Long idComprador, Long idVendedor);
	
}
