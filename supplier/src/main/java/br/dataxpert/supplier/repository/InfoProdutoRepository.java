package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.InfoProduto;

public interface InfoProdutoRepository {

	List<InfoProduto> ObterInfoProdutoPorEAN(String filial, String ean);
	
}
