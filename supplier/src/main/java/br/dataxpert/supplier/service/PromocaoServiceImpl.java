package br.dataxpert.supplier.service;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.repository.PromocaoRepository;

public class PromocaoServiceImpl implements PromocaoService {

	public PromocaoRepository repository;

	 public boolean SalvarPromocao(Promocao promocao) {

		return repository.SalvarPromocao(promocao);
		
	}

}
