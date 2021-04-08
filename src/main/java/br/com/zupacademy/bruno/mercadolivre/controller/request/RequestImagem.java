package br.com.zupacademy.bruno.mercadolivre.controller.request;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class RequestImagem {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public RequestImagem(@Size(min = 1) @NotNull List<MultipartFile> files) {
		super();
		this.imagens = files;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public Set<String> imagensDuplicadas() {
		HashSet<String> duplicados = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (MultipartFile imagem : imagens) {
			String file = imagem.getOriginalFilename();
			if (!duplicados.add(file)) {
				resultados.add(file);
			}
		}
		return resultados;
	}
	
	
	
	
	
}
