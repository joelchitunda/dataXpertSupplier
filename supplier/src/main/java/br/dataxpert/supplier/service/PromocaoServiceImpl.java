package br.dataxpert.supplier.service;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.repository.PromocaoRepository;

@Service
public class PromocaoServiceImpl implements PromocaoService {

	public PromocaoRepository repository;

	 public boolean SalvarPromocao(Promocao promocao) {

		return repository.SalvarPromocao(promocao);
		
	}

}
