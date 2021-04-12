package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.bruno.mercadolivre.controller.request.RequestCaracteristica;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;
	@NotNull
	@Positive
	@Column(nullable = false)
	private BigDecimal valor;
	@NotNull
	@PositiveOrZero
	@Column(nullable = false)
	private Integer quantidade;
	@NotBlank
	@Size(max = 1000)
	@Column(nullable = false, length = 1000)
	private String descricao;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Categoria categoria;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<ProdutoCaracteristica> caracteristicas = new HashSet<>();

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "produto")
	private Set<ImagemProduto> imagens = new HashSet<>();

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "produto")
	private Set<OpiniaoProduto> opinioes = new HashSet<>();

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "produto")
	private Set<PerguntaProduto> perguntas = new HashSet<>();

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Size(max = 1000) String descricao,
			@Size(min = 3) @Valid Collection<RequestCaracteristica> caracteristicas, Categoria categoria,
			Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		Assert.isTrue(caracteristicas.size() >= 3, "Um produto precisa de no mínimo três características.");
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
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

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Set<ProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<OpiniaoProduto> getOpinioes() {
		return opinioes;
	}
	
	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public void addImagem(Set<String> links, Usuario user) {
		if(!usuario.getUsername().equals(user.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode adicionar imagens a um produto que não é seu.");
		}
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);

	}

	public void adicionaOpiniao(@Valid OpiniaoProduto opiniao, Usuario user) {
		if(usuario.getUsername().equals(user.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode opinar seu próprio produto.");
		}
		this.opinioes.add(opiniao);

	}

	public void adicionaPergunta(PerguntaProduto pergunta, Usuario user) {
		if(usuario.getUsername().equals(user.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode perguntar sobre seu próprio produto.");
		}
		this.perguntas.add(pergunta);

	}
	
	public <T> Set<T> mapCaracteristicas(Function<ProdutoCaracteristica, T> mapper){
		return this.caracteristicas.stream().map(mapper).collect(Collectors.toSet());
	}

	public <T> Set<T> mapPerguntas(Function<PerguntaProduto, T> mapper){
		return this.perguntas.stream().map(mapper).collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapOpiniao(Function<OpiniaoProduto, T> mapper){
		return this.opinioes.stream().map(mapper).collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapImagem(Function<ImagemProduto, T> mapper){
		return this.imagens.stream().map(mapper).collect(Collectors.toSet());
	}
	
	public int totalDeNotas() {
		return opinioes.size();
	}
	
	public OptionalDouble mediaNotas() {
		return this.opinioes.stream().mapToInt(opiniao ->  opiniao.getNota()).average();
	}

	public void abaterEstoque(@NotNull @Positive Integer quantidade) {
		if(quantidade > this.quantidade) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O estoque do produto: " + this.quantidade + ", é menor que o pedido:" + quantidade);
		}
		this.quantidade -= quantidade;		
	}
	
	public void validaUsuario(Usuario usuario) {
		if(this.usuario.getUsername().equals(usuario.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode comprar seu próprio produto.");
		}
	}
}
