package br.dataxpert.supplier.service;

import java.util.List;

import br.dataxpert.supplier.model.ItemPedidoFilial;
import br.dataxpert.supplier.model.PedidoFilial;
import br.dataxpert.supplier.model.ResumoPedidoFornecedor;

public interface PedidoFilialService {

	List<PedidoFilial> ObterPedidoFilialPorFornecedor(String cnpj, String ano, String mes);

	List<ItemPedidoFilial> ObterItemPedidoFilialPorPedido(String filial, String pedido);

	List<ResumoPedidoFornecedor> ObterResumoPedidosFornecedor(String cnpj);

}
