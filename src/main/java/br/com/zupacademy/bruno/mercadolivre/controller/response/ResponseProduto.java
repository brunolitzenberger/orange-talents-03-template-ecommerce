package br.com.zupacademy.bruno.mercadolivre.controller.response;

import java.math.BigDecimal;
import java.util.OptionalDouble;
import java.util.Set;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;

public class ResponseProduto {

	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Set<ResponseCaracteristica> caracteristicas;
	private Set<Object> imagens;
	private String usuario;
	private Set<ResponseOpiniao> opinioes;
	private int totalNotas;
	private OptionalDouble mediaNotas;
	
	@Deprecated
	public ResponseProduto() {
		
	}
	
	public ResponseProduto(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.usuario = produto.getUsuario().getUsername();
		this.imagens = produto.mapImagem(ResponseImagem::new);
		this.caracteristicas = produto.mapCaracteristicas(ResponseCaracteristica::new);
		this.opinioes = produto.mapOpiniao(ResponseOpiniao::new);
		this.totalNotas = produto.totalDeNotas();
		this.mediaNotas = produto.mediaNotas();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<ResponseCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<Object> getImagens() {
		return imagens;
	}

	public String getUsuario() {
		return usuario;
	}

	public Set<ResponseOpiniao> getOpinioes() {
		return opinioes;
	}

	public int getTotalNotas() {
		return totalNotas;
	}

	public OptionalDouble getMediaNotas() {
		return mediaNotas;
	}
	
	public static ResponseProduto converter(Produto produto) {
		return new ResponseProduto(produto);
	}
}
