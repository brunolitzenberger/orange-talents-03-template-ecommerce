package br.com.zupacademy.bruno.mercadolivre.controller.request;

public class RequestRakingVendedores {

	private Long idCompra;
	private Long idVendedor;

	public RequestRakingVendedores(Long idCompra, Long idVendedor) {
		super();
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}

	@Override
	public String toString() {
		return "RakingVendedoresRequest [idCompra=" + idCompra + ", idVendedor=" + idVendedor + "]";
	}
	
	

}
