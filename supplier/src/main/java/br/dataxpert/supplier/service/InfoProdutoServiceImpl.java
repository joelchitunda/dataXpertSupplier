package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.InfoProduto;
import br.dataxpert.supplier.repository.InfoProdutoRepository;

@Service
public class InfoProdutoServiceImpl implements InfoProdutoService {

	InfoProdutoRepository repository;

	public List<InfoProduto> ObterInfoProdutoPorEAN(String filial, String ean) {

		return repository.ObterInfoProdutoPorEAN(filial, ean);

	}

}
