package br.com.zupacademy.bruno.mercadolivre.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Categoria;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestCaracteristica;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.SenhaValida;

public class ProdutoTest {

	@DisplayName("um produto precisa de no mínimo três características")
	@ParameterizedTest
	@MethodSource("geradorDeTeste1")
	public void teste1(Collection<RequestCaracteristica> caracteristicas) {
		Categoria categoria = new Categoria("Ferramentas");
		Usuario usuario = new Usuario("marina@zup.com.br", new SenhaValida("220592"));
		new Produto("Furadeira", BigDecimal.TEN, 2, "Uma furadeira qualquer", caracteristicas, categoria, usuario);
	}
	
	static Stream<Arguments> geradorDeTeste1(){
		return Stream.of
				(Arguments.of(
					List.of(new RequestCaracteristica("Broca", "15mm"),
							new RequestCaracteristica("Voltagem", "15w"),
							new RequestCaracteristica("Peso", "1KG")
						)),
				Arguments.of(
						List.of(new RequestCaracteristica("a1", "a1"),
								new RequestCaracteristica("a2", "a2"),
								new RequestCaracteristica("a3", "a3"),
								new RequestCaracteristica("a4", "a4")
						)));
	}
	
	@DisplayName("um produto não pode ser criado com menos de 3 características")
	@ParameterizedTest
	@MethodSource("geradorDeTeste2")
	public void teste2(Collection<RequestCaracteristica> caracteristicas) {
		Categoria categoria = new Categoria("Ferramentas");
		Usuario usuario = new Usuario("marina@zup.com.br", new SenhaValida("220592"));
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Produto("Furadeira", BigDecimal.TEN, 2, "Uma furadeira qualquer", caracteristicas, categoria, usuario);
		});
	}
	
	static Stream<Arguments> geradorDeTeste2(){
		return Stream.of
				(Arguments.of(
					List.of(new RequestCaracteristica("Broca", "15mm"),
							new RequestCaracteristica("Voltagem", "15w")
						)),
				Arguments.of(
						List.of(new RequestCaracteristica("a1", "a1")
						)));
	}
	
	
}
