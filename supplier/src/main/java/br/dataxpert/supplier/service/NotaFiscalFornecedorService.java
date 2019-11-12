package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.NotaFiscalFornecedor;

public interface NotaFiscalFornecedorService {

	List<NotaFiscalFornecedor> ObterNotaFiscalPorFornecedor(String cnpj, String ano);

}
