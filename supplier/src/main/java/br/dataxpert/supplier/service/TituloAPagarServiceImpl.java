package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.TituloAPagar;
import br.dataxpert.supplier.repository.TituloAPagarRepository;

@Service
public class TituloAPagarServiceImpl implements TituloAPagarService {

	public TituloAPagarRepository repository;
	

	public List<TituloAPagar> ObterTituloAPagarPorFornecedor(String cnpj, String ano) {

		return repository.ObterTituloAPagarPorFornecedor(cnpj, ano);

	}

	public List<TituloAPagar> ObterTotalAPagarPorFornecedor(String cnpj, String ano) {

		return repository.ObterTotalAPagarPorFornecedor(cnpj, ano);

	}

}
