package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.Produto;


public interface ProdutoRepository {

	List<Produto> ObterProdutoEanPorDescricao(String descricao);

}


	



