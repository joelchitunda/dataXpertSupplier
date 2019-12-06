package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.dataxpert.supplier.model.VendaProduto;
import br.dataxpert.supplier.service.VendaProdutoService;

@Controller
@RequestMapping("api/vendaproduto")
public class VendaProdutoController {
	// public static final Logger logger =
	// LoggerFactory.getLogger(ChatBotButtonController.class);

	@Autowired
	private VendaProdutoService vendaProdutoService;

	@RequestMapping(value = "/ObterVendaProdutoPorFornecedor", method = RequestMethod.GET)
	public ResponseEntity<List<VendaProduto>> ObterVendaProdutoPorFornecedor(
			@RequestParam(value = "cnpjfornecedor") String cnpjfornecedor,
			@RequestParam(value = "descricao") String descricao, @RequestParam(value = "ano") String ano) {

		List<VendaProduto> results = vendaProdutoService.ObterVendaProdutoPorFornecedor(cnpjfornecedor, descricao, ano);
		List<VendaProduto> vendas = results;

		if (vendas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<VendaProduto>>(vendas, HttpStatus.OK);

	}

}
