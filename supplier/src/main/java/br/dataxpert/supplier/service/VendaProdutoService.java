package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.VendaProduto;

public interface VendaProdutoService {

	List<VendaProduto> ObterVendaProdutoPorFornecedor(String cnpjfornecedor, String descricao, String ano);

}
