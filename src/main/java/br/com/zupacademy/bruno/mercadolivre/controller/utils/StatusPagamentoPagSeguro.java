package br.com.zupacademy.bruno.mercadolivre.controller.utils;

public enum StatusPagamentoPagSeguro{
	SUCESSO,
	ERRO;
	
	public StatusPagamento converter() {
		if(this.equals(SUCESSO)) {
			return StatusPagamento.SUCESSO;
		}
		return StatusPagamento.FALHA;
	}
}
