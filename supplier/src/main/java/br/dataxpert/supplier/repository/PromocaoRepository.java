package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.model.PromocaoItem;

public interface PromocaoRepository {

	String SalvarPromocao(Promocao promocao);

	boolean SalvarPromocaoItens(List<PromocaoItem> plist);

}
