package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.EstoqueFilial;

public interface EstoqueFilialRepository {

	List<EstoqueFilial> ObterEstoqueFilialPorEAN(String filial, String ean);

	List<EstoqueFilial> ObterEstoquePorFornecedor(String cnpjfornecedor, String descricao);

}
