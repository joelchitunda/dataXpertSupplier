package br.dataxpert.supplier.model;

public class PromocaoItem {

	public String promocaoId;
	public String codprod;
	public String qtde_min;
	public String preco_real;
	public String perc_desc;
	public String preco_promo;
	public String prazo_pagto;

	
	public String getPromocaoId() {

		return promocaoId;

	}

	public void setPromocaoId(String promocaoId) {

		this.promocaoId = promocaoId;

	}

	public String getCodprod() {

		return codprod;

	}

	public void setCodprod(String codprod) {

		this.codprod = codprod;

	}

	public String getQtde_min() {

		return qtde_min;

	}

	public void setQtde_min(String qtde_min) {

		this.qtde_min = qtde_min;

	}

	public String getPreco_real() {

		return preco_real;

	}

	public void setPreco_real(String preco_real) {

		this.preco_real = preco_real;

	}

	public String getPerc_desc() {

		return perc_desc;

	}

	public void setPerc_desc(String perc_desc) {

		this.perc_desc = perc_desc;

	}

	public String getPreco_promo() {

		return preco_promo;

	}

	public void setPreco_promo(String preco_promo) {

		this.preco_promo = preco_promo;

	}

	public String getPrazo_pagto() {

		return prazo_pagto;

	}

	public void setPrazo_pagto(String prazo_pagto) {

		this.prazo_pagto = prazo_pagto;

	}

}
