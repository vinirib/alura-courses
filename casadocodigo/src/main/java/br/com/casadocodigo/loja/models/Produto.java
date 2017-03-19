package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicUpdate
@DynamicInsert
public class Produto {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String titulo;
	@Column(nullable=false, columnDefinition="TEXT")
	private String descricao;
	@Column(nullable=false)
	private int paginas;
	@Transient
	private String imageFile;
	
	@ElementCollection
	private List<Preco> precos = new ArrayList<>();

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataLancamento;
	private String sumarioPath;

	@ElementCollection(targetClass=Categoria.class, fetch=FetchType.EAGER)
	@CollectionTable(name="produto_categoria", joinColumns = @JoinColumn(name = "categoria_id"))
	@Column(name="categoria", nullable=false)
	@Enumerated(EnumType.STRING)
	private List<Categoria> categorias;
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}
	
	public String getImageFile() {
		return imageFile;
	}
	
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

	public BigDecimal precoPara(TipoPreco tipoPreco) {
	    return precos.stream().filter(preco -> preco.getPreco().equals(tipoPreco)).findFirst().get().getValor();        
	}
	
}
