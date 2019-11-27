package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.dataxpert.supplier.model.EstoqueFilial;
import br.dataxpert.supplier.service.EstoqueFilialService;

@Controller
@RequestMapping("/estoquefilial")
public class EstoqueFilialController {

	// public static final Logger logger =
	// LoggerFactory.getLogger(ChatBotButtonController.class);

	@Autowired
	private EstoqueFilialService estoqueFilialService;

	@RequestMapping(value = "/ObterEstoquePorFornecedor/", method = RequestMethod.GET)
	public ResponseEntity<List<EstoqueFilial>> ObterEstoquePorFornecedor(@PathVariable String cnpjfornecedor,
			@PathVariable String descricao) {

		List<EstoqueFilial> estoques = estoqueFilialService.ObterEstoquePorFornecedor(cnpjfornecedor, descricao);

		if (estoques.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<EstoqueFilial>>(estoques, HttpStatus.OK);

	}

	@RequestMapping(value = "/ObterEstoqueFilialPorEAN/", method = RequestMethod.GET)
	public ResponseEntity<List<EstoqueFilial>> ObterEstoqueFilialPorEAN(@PathVariable String filial,
			@PathVariable String ean) {

		List<EstoqueFilial> estoques = estoqueFilialService.ObterEstoqueFilialPorEAN(filial, ean);

		if (estoques.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<EstoqueFilial>>(estoques, HttpStatus.OK);

	}

}
