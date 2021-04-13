package br.com.zupacademy.bruno.mercadolivre.controller;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Compra;

@Service
public class Ranking {

	public void gera(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getUsuario().getId());
		restTemplate.postForEntity("http://localhost:8080/ranking-vendedores", request, String.class);		
	}

}
