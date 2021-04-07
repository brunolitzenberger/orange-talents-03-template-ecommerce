package br.com.zupacademy.bruno.mercadolivre.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestCaracteristica;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestProduto;

public class RequestProdutoTest {

	
	
	@DisplayName("cria produto com diversos tipos de caracteristicas")
	@ParameterizedTest
	@MethodSource("gerador")
	public void teste1(int esperado, List<RequestCaracteristica> caracteristicas) {
		RequestProduto request = new RequestProduto("Furadeira", BigDecimal.TEN, 2, caracteristicas, "asyasgya", 1L);
		Assertions.assertEquals(esperado, request.caracteristicasDuplicadas().size());
	}
	
	static Stream<Arguments> gerador(){
		return Stream.of
				(Arguments.of(0, List.of()),
				Arguments.of(0, List.of(new RequestCaracteristica("key", "value"))),
				Arguments.of(0, List.of(new RequestCaracteristica("key", "value"), new RequestCaracteristica("key1", "value1"))),
				Arguments.of(1, List.of(
						new RequestCaracteristica("key", "value"),
						new RequestCaracteristica("key", "value")))
				);
	}
	
	
}
