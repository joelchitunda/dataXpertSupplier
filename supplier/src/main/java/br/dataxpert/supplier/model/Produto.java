package br.dataxpert.supplier.model;

public class Produto {
	
	private String codproduto;
	private String descproduto;
	private String ean;
	private String classevenda;
	
	
	
	public String getCodproduto() {
		
		return codproduto;
		
	}
	
	public void setCodproduto(String codproduto) {
		
		this.codproduto = codproduto;
		
	}
	
	public String getDescproduto() {
		
		return descproduto;
		
	}
	
	public void setDescproduto(String descproduto) {
		
		this.descproduto = descproduto;
		
	}
	
	public String getEan() {
		
		return ean;
		
	}
	
	public void setEan(String ean) {
		
		this.ean = ean;
		
	}
	
	public String getClassevenda() {
		
		return classevenda;
		
	}
	
	public void setClassevenda(String classevenda) {
		
		this.classevenda = classevenda;
		
	}
	
}
