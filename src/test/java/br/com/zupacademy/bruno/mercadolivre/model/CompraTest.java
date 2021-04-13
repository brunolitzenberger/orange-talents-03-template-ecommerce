package br.com.zupacademy.bruno.mercadolivre.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Categoria;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Transacao;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestCaracteristica;
import br.com.zupacademy.bruno.mercadolivre.controller.response.PagamentoResponse;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.SenhaValida;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusPagamento;

public class CompraTest {

	@Test
	@DisplayName("aceitar uma transacao com sucesso")
	void deveriaAdicionarUmaTransacaoComSucesso() {
		Compra novaCompra = novaCompra();
		PagamentoResponse pagamentoResponse = (compra) -> {
			return new Transacao(compra, "1", StatusPagamento.SUCESSO);
		};
		
		novaCompra.adicionaTransacao(pagamentoResponse);
	}
	
	@Test
	@DisplayName("não pode aceitar mais de uma transacao com sucesso")
	void naoDeveriaAdicionarMaisDeUmaTransacaoComSucesso() {
		Compra novaCompra = novaCompra();
		PagamentoResponse pagamentoResponse = (compra) -> {
			return new Transacao(compra, "1", StatusPagamento.SUCESSO);
		};
		
		novaCompra.adicionaTransacao(pagamentoResponse);
		
		PagamentoResponse pagamentoResponse2 = (compra) -> {
			return new Transacao(compra, "2", StatusPagamento.SUCESSO);
		};
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			novaCompra.adicionaTransacao(pagamentoResponse2);
		});
	}
	
	@Test
	@DisplayName("não pode aceitar transacao com erro quando ja foi concluido com sucesso")
	void naoPodeAceitarTransacaoComErroQuandoJaFoiConcluidaComSucesso() {
		Compra novaCompra = novaCompra();
		PagamentoResponse pagamentoResponse = (compra) -> {
			return new Transacao(compra, "1", StatusPagamento.SUCESSO);
		};
		
		novaCompra.adicionaTransacao(pagamentoResponse);
		
		PagamentoResponse pagamentoResponse2 = (compra) -> {
			return new Transacao(compra, "2", StatusPagamento.FALHA);
		};
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			novaCompra.adicionaTransacao(pagamentoResponse2);
		});
	}
	
	private Compra novaCompra() {
		Categoria categoria = new Categoria("teste");
		Usuario dono = new Usuario("email@email.com", new SenhaValida("senhaa"));
		Collection<RequestCaracteristica> caracteristicas = new ArrayList<>();
		caracteristicas.add(new RequestCaracteristica("nome", "descricao"));
		caracteristicas	
				.add(new RequestCaracteristica("nome1", "descricao"));
		caracteristicas
				.add(new RequestCaracteristica("nome2", "descricao"));

		Produto produtoASerComprado = new Produto("teste", BigDecimal.TEN, 100, "descricao", caracteristicas,
				 categoria, dono);

		Usuario comprador = new Usuario("comprador@email.com",
				new SenhaValida("senhaa"));


		return new Compra(produtoASerComprado, 50, comprador, BigDecimal.TEN);
	}
}
