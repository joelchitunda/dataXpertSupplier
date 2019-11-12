package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.EstoqueFilial;

public interface EstoqueFilialService {

	List<EstoqueFilial> ObterEstoqueFilialPorEAN(String filial, String ean);

	List<EstoqueFilial> ObterEstoquePorFornecedor(String cnpjfornecedor, String descricao);

}
