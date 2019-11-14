package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.Produto;


public interface ProdutoService {

	List<Produto> ObterProdutoEanPorDescricao(String descricao);

}
