package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.InfoProduto;

public interface InfoProdutoService {

	List<InfoProduto> ObterInfoProdutoPorEAN(String filial, String ean);

}
