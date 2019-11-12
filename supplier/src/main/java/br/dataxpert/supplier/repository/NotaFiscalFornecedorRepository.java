package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.NotaFiscalFornecedor;

public interface NotaFiscalFornecedorRepository {

	List<NotaFiscalFornecedor> ObterNotaFiscalPorFornecedor(String cnpj, String ano);

}
