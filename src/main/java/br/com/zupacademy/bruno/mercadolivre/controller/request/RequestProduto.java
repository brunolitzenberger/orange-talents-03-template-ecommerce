package br.com.zupacademy.bruno.mercadolivre.controller.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.bruno.mercadolivre.controller.model.Categoria;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Produto;
import br.com.zupacademy.bruno.mercadolivre.controller.model.Usuario;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.annotations.UniqueValue;

public class RequestProduto {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "Produto já cadastrado.")
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	@Size(min = 3)
	@Valid
	private List<RequestCaracteristica> caracteristicas = new ArrayList<>();
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	@NotNull
	private Long categoriaId;

	public RequestProduto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @Size(min = 3) @Valid List<RequestCaracteristica> caracteristicas,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Long categoriaId) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas.addAll(caracteristicas);
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public List<RequestCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public Produto toModel(EntityManager em, Usuario usuario) {
		return new Produto(nome, valor, quantidade, descricao, caracteristicas, em.find(Categoria.class, categoriaId), usuario);
	}

	public Set<String> caracteristicasDuplicadas() {
		HashSet<String> duplicados = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (RequestCaracteristica caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			if (!duplicados.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

}
