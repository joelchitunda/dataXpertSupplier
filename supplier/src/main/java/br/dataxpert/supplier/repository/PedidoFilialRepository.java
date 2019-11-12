package br.dataxpert.supplier.repository;

import java.util.List;

import br.dataxpert.supplier.model.ItemPedidoFilial;
import br.dataxpert.supplier.model.PedidoFilial;
import br.dataxpert.supplier.model.ResumoPedidoFornecedor;

public interface PedidoFilialRepository {

	List<PedidoFilial> ObterPedidoFilialPorFornecedor(String cnpj, String ano, String mes);

	List<ItemPedidoFilial> ObterItemPedidoFilialPorPedido(String filial, String pedido);

	List<ResumoPedidoFornecedor> ObterResumoPedidosFornecedor(String cnpj);

}
