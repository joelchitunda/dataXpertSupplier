package br.dataxpert.supplier.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.ItemPedidoFilial;
import br.dataxpert.supplier.model.PedidoFilial;
import br.dataxpert.supplier.model.ResumoPedidoFornecedor;

@Repository
public class PedidoFilialRepositoryImpl implements PedidoFilialRepository {

	public List<PedidoFilial> ObterPedidoFilialPorFornecedor(String cnpj, String ano, String mes) {
		// TODO Auto-generated method stub
		return null;

	}

	public List<ItemPedidoFilial> ObterItemPedidoFilialPorPedido(String filial, String pedido) {
		// TODO Auto-generated method stub
		return null;

	}

	public List<ResumoPedidoFornecedor> ObterResumoPedidosFornecedor(String cnpj) {
		// TODO Auto-generated method stub
		return null;

	}

}
