package br.dataxpert.supplier.model;

public class Promocao {
	
	private String codigo;
	private String titulo;
	private String inicio;
	private String termino;
	private String fornecedor;
	private String data;
	private String usuario;

	
	public String getCodigo() {
		
		return codigo;
		
	}

	public void setCodigo(String codigo) {
		
		this.codigo = codigo;
		
	}

	public String getTitulo() {
		
		return titulo;
		
	}

	public void setTitulo(String titulo) {
		
		this.titulo = titulo;
		
	}

	public String getInicio() {
		
		return inicio;
		
	}

	public void setInicio(String inicio) {
		
		this.inicio = inicio;
		
	}

	public String getTermino() {
		
		return termino;
		
	}
	
	public void setTermino(String termino) {
		
		this.termino = termino;
		
	}

	public String getFornecedor() {
		
		return fornecedor;
		
	}

	public void setFornecedor(String fornecedor) {
		
		this.fornecedor = fornecedor;
		
	}

	public String getData() {
		
		return data;
		
	}

	public void setData(String data) {
		
		this.data = data;
		
	}

	public String getUsuario() {
		
		return usuario;
		
	}

	public void setUsuario(String usuario) {
		
		this.usuario = usuario;
		
	}

}
