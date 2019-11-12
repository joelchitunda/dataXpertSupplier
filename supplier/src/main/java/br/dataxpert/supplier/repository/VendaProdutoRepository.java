package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.VendaProduto;

public interface VendaProdutoRepository {

	List<VendaProduto> ObterVendaProdutoPorFornecedor(String cnpjfornecedor, String descricao, String ano);

}
