package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.TituloAPagar;

public interface TituloAPagarService {

	List<TituloAPagar> ObterTituloAPagarPorFornecedor(String cnpj, String ano);

	List<TituloAPagar> ObterTotalAPagarPorFornecedor(String cnpj, String ano);

}
