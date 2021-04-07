package br.com.zupacademy.bruno.mercadolivre.validator;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestProduto;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return RequestProduto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		RequestProduto request = (RequestProduto) target;
		Set<String> duplicados = request.caracteristicasDuplicadas();
		if(!duplicados.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Característica duplicada: "+duplicados);
		}
	}

}
