package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.NotaFiscalFornecedor;
import br.dataxpert.supplier.repository.NotaFiscalFornecedorRepository;

@Service
public class NotaFiscalFornecedorServiceImpl implements NotaFiscalFornecedorService {

	public NotaFiscalFornecedorRepository repository;
	

	public List<NotaFiscalFornecedor> ObterNotaFiscalPorFornecedor(String cnpj, String ano) {

		return repository.ObterNotaFiscalPorFornecedor(cnpj, ano);

	}

}
