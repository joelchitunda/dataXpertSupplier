package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.model.PromocaoItem;
import br.dataxpert.supplier.repository.PromocaoRepository;

@Service
public class PromocaoServiceImpl implements PromocaoService {

	public PromocaoRepository repository;

	 public String SalvarPromocao(Promocao promocao) {

		return repository.SalvarPromocao(promocao);
		
	}

	@Override
	public boolean SalvarPromocaoItens(List<PromocaoItem> plist) {

		return repository.SalvarPromocaoItens(plist);
		
	}

}
