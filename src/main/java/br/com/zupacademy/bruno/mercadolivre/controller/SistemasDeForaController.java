package br.com.zupacademy.bruno.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestNotaFiscal;
import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestRakingVendedores;

@RestController
@RequestMapping
public class SistemasDeForaController {

	@PostMapping("/notas-fiscais")
	public String processaNf(@Valid @RequestBody RequestNotaFiscal request) {
		return request.toString();		
	}
	
	@PostMapping("/ranking-vendedores")
	public String geraRaking(@Valid @RequestBody RequestRakingVendedores request) {
		return request.toString();
	}
	
}
