package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.model.PromocaoItem;

public interface PromocaoService {

	 String SalvarPromocao(Promocao promocao); 
	 
	 boolean SalvarPromocaoItens(List<PromocaoItem> plist);
	 
}
