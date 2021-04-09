package br.com.zupacademy.bruno.mercadolivre.controller.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;

@Component
public class SimuladorDeUpload {

	public  Set<String> enviar(List<MultipartFile> imagens, Produto produto) {
		return imagens.stream().map(imagem -> "http://algumbucketdeimagens/" + "MLB-"  + produto.getId() + "-"+ produto.getNome().replaceAll("\\s+", "-").toLowerCase() + "-" + imagem.getOriginalFilename()).collect(Collectors.toSet());
	}

	
	
}
