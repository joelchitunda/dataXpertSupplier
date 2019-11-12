package br.dataxpert.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.model.ItemPedidoFilial;
import br.dataxpert.supplier.model.PedidoFilial;
import br.dataxpert.supplier.model.ResumoPedidoFornecedor;
import br.dataxpert.supplier.repository.PedidoFilialRepository;

@Service
public class PedidoFilialServiceImpl implements PedidoFilialService {

	public PedidoFilialRepository repository;
	

	public List<PedidoFilial> ObterPedidoFilialPorFornecedor(String cnpj, String ano, String mes) {

		return repository.ObterPedidoFilialPorFornecedor(cnpj, ano, mes);

	}

	public List<ItemPedidoFilial> ObterItemPedidoFilialPorPedido(String filial, String pedido) {

		return repository.ObterItemPedidoFilialPorPedido(filial, pedido);

	}

	public List<ResumoPedidoFornecedor> ObterResumoPedidosFornecedor(String cnpj) {

		return repository.ObterResumoPedidosFornecedor(cnpj);

	}

}
