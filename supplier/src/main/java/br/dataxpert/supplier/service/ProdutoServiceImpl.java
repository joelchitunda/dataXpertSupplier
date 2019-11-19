package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.Produto;
import br.dataxpert.supplier.repository.ProdutoRepository;


@Service
public class ProdutoServiceImpl implements ProdutoService {

	public ProdutoRepository repository;

	public List<Produto> ObterProdutoEanPorDescricao(String descricao) {

		return repository.ObterProdutoEanPorDescricao(descricao);

	}

	public List<Produto> ObterProdutoPorDescricao(String cnpjfornecedor, String descricao) {
		
		return repository.ObterProdutoPorDescricao(cnpjfornecedor,descricao);
	}

}
