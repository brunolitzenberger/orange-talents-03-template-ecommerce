package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusCompra;

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
	
	@Deprecated
	public Compra() {
		
	}	

	public Compra(Produto produto, @Positive @NotNull Integer quantidade, Usuario comprador,
			BigDecimal valor) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.valor = valor;
	}



	public Long getId() {
		return id;
	}

	
	
	

}
