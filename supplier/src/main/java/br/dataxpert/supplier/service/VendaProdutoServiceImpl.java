package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.VendaProduto;
import br.dataxpert.supplier.repository.VendaProdutoRepository;

@Service
public class VendaProdutoServiceImpl implements VendaProdutoService {

	public VendaProdutoRepository repository;
	

	public List<VendaProduto> ObterVendaProdutoPorFornecedor(String cnpjfornecedor, String descricao, String ano) {

		return repository.ObterVendaProdutoPorFornecedor(cnpjfornecedor, descricao, ano);

	}

}
