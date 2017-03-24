package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Preco {

	@Column(nullable=false)
	private BigDecimal valor;
	@Column(nullable=false)
	private TipoPreco preco;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getPreco() {
		return preco;
	}
	public void setPreco(TipoPreco preco) {
		this.preco = preco;
	}
	
	public String toString() {
	    return this.preco.name() + " - " + this.valor;
	}
	
}
