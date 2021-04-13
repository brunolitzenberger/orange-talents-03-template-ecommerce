package br.com.zupacademy.bruno.mercadolivre.controller.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import br.com.zupacademy.bruno.mercadolivre.controller.utils.StatusPagamento;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Compra compra;
	@NotBlank
	private String idTransacao;
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;
	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Transacao() {

	}

	public Transacao(Compra compra, String idPagamento, StatusPagamento status) {
		this.compra = compra;
		this.idTransacao = idPagamento;
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		return true;
	}

	public boolean transacoesConcluidas() {
		return status.equals(StatusPagamento.SUCESSO);
	}

	public StatusPagamento getStatus() {
		return status;
	}
	
	
	
	
}
