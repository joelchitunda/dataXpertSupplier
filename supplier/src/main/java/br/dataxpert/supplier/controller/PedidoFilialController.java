package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.dataxpert.supplier.model.ItemPedidoFilial;
import br.dataxpert.supplier.model.PedidoFilial;
import br.dataxpert.supplier.model.Produto;
import br.dataxpert.supplier.model.ResumoPedidoFornecedor;
import br.dataxpert.supplier.service.PedidoFilialService;
import br.dataxpert.supplier.service.ProdutoService;

@Controller
@RequestMapping("api/pedidofilial")
public class PedidoFilialController {

	// public static final Logger logger =
	// LoggerFactory.getLogger(ChatBotButtonController.class);

	@Autowired
	private PedidoFilialService pedidoFilialService;

	@RequestMapping(value = "/ObterPedidoFilialPorFornecedor", method = RequestMethod.GET)
	public ResponseEntity<List<PedidoFilial>> ObterPedidoFilialPorFornecedor(@RequestParam(value="cnpj") String cnpj,
			@RequestParam(value="ano") String ano, @RequestParam(value="mes") String mes) {

		List<PedidoFilial> pedidos = pedidoFilialService.ObterPedidoFilialPorFornecedor(cnpj, ano, mes);

		if (pedidos.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<PedidoFilial>>(pedidos, HttpStatus.OK);

	}

	@RequestMapping(value = "/ObterItemPedidoFilialPorPedido", method = RequestMethod.GET)
	public ResponseEntity<List<ItemPedidoFilial>> ObterItemPedidoFilialPorPedido(@RequestParam(value="filial") String filial,
			@RequestParam(value="pedido") String pedido) {

		List<ItemPedidoFilial> itens = pedidoFilialService.ObterItemPedidoFilialPorPedido(filial, pedido);

		if (itens.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<ItemPedidoFilial>>(itens, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/ObterResumoPedidosFornecedor", method = RequestMethod.GET)
	public ResponseEntity<List<ResumoPedidoFornecedor>> ObterResumoPedidosFornecedor(@RequestParam(value="cnpj") String cnpj) {

		List<ResumoPedidoFornecedor> resumos = pedidoFilialService.ObterResumoPedidosFornecedor(cnpj);

		if (resumos.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<ResumoPedidoFornecedor>>(resumos, HttpStatus.OK);

	}

}
