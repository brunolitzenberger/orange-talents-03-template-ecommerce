package br.com.zupacademy.bruno.mercadolivre.validator;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestImagem;

public class ProibeImagensIguaisValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RequestImagem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		RequestImagem request = (RequestImagem) target;
		Set<String> duplicados = request.imagensDuplicadas();
		if(!duplicados.isEmpty()) {
			errors.rejectValue("imagens", null, "Você enviou duas imagens iguais.");
		}

	}

}
