package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.EstoqueFilial;
import br.dataxpert.supplier.repository.EstoqueFilialRepository;

@Service
public class EstoqueFilialServiceImpl implements EstoqueFilialService {

	public EstoqueFilialRepository repository;
	

	public List<EstoqueFilial> ObterEstoqueFilialPorEAN(String filial, String ean) {

		return repository.ObterEstoqueFilialPorEAN(filial, ean);

	}

	public List<EstoqueFilial> ObterEstoquePorFornecedor(String cnpjfornecedor, String descricao) {

		return repository.ObterEstoqueFilialPorEAN(cnpjfornecedor, descricao);

	}
}
