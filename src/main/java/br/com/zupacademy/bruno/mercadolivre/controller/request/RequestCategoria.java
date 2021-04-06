package br.com.zupacademy.bruno.mercadolivre.controller.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Categoria;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.annotations.UniqueValue;

public class RequestCategoria {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	@Positive
	private Long idCategoriaMae;
	
	public RequestCategoria(String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(EntityManager em) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae != null) {
			categoria.setCategoria(em.find(Categoria.class, idCategoriaMae));
		}
		return categoria;
	}
	
	
	

}
