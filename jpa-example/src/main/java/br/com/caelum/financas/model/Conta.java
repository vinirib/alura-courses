package br.com.caelum.financas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String titular;
    private String banco; 
    private String agencia;
    private String numero;
    
    @OneToMany(mappedBy="conta")
    private List<Movimentacao> movimentacoes;
    
    
    public Conta() {
	}
    
	public Conta(String titular, String banco, String agencia, String numero) {
		super();
		this.titular = titular;
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
	}
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
    
	

}
