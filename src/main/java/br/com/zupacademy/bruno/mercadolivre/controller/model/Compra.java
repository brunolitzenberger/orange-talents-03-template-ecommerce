package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.bruno.mercadolivre.controller.response.PagamentoResponse;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusCompra;
import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusPagamento;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Produto produto;
	@Positive
	@NotNull
	private Integer quantidade;

	@ManyToOne
	private Usuario comprador;

	@Positive
	@NotNull
	private BigDecimal valor;

	@Enumerated(EnumType.STRING)
	private StatusCompra statusCompra = StatusCompra.INICIADO;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Compra() {

	}

	public Compra(Produto produto, @Positive @NotNull Integer quantidade, Usuario comprador, BigDecimal valor) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	public StatusPagamento adicionaTransacao(@Valid PagamentoResponse pagamentoResponse) {
		Transacao novaTransacao = pagamentoResponse.toModel(this);
		Assert.state(!this.transacoes.contains(novaTransacao),
				"Já existe uma transacao igual a essa processada "
						+ novaTransacao);
		Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");
		transacoes.add(novaTransacao);
		return novaTransacao.getStatus();
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidas = this.transacoes.stream().filter(Transacao::transacoesConcluidas)
				.collect(Collectors.toSet());
		Assert.isTrue(transacoesConcluidas.size() <=1, "Esta compra já foi concluída.");
		return transacoesConcluidas;
	}

	public String sucessoCompra() {
		return "Olá " + comprador + " sua compra do produto: " + produto.getNome() + ", com quantidade de :"
				+ quantidade + "foi concluída com sucesso.";
	}

	public String falhaCompra() {
		return "Olá " + comprador + " sua compra do produto: " + produto.getNome() + ", com quantidade de :"
				+ quantidade + "falhou, por favor tente novamente";
	}
	
	public boolean compraComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

}
