package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscal {

	public void gera(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
		restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
	}
	
}
