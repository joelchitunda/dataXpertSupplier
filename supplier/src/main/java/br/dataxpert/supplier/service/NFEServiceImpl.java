package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.NotaFiscalFornecedor;
import br.dataxpert.supplier.repository.NFERepository;

@Service
public class NFEServiceImpl implements NFEService {

	public NFERepository repository;
	

	public List<NotaFiscalFornecedor> ObterNotaFiscalPorFornecedor(String cnpj, String ano) {

		return repository.ObterNotaFiscalPorFornecedor(cnpj, ano);
	}

}
