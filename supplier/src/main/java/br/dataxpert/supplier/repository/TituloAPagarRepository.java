package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.TituloAPagar;

public interface TituloAPagarRepository {

	List<TituloAPagar> ObterTituloAPagarPorFornecedor(String cnpj, String ano);

	List<TituloAPagar> ObterTotalAPagarPorFornecedor(String cnpj, String ano);

}
