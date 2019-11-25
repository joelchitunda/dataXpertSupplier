package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.dataxpert.supplier.model.Produto;
import br.dataxpert.supplier.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	// public static final Logger logger =
	// LoggerFactory.getLogger(ChatBotButtonController.class);

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/ObterProdutoPorDescricao/", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> ObterProdutoPorDescricao(@PathVariable String cnpjfornecedor, @PathVariable String descricao) {
		List<Produto> produtos = produtoService.ObterProdutoPorDescricao(cnpjfornecedor, descricao);
		if (produtos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/ObterProdutoEanPorDescricao/", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> ObterProdutoEanPorDescricao(@PathVariable String descricao) {
		List<Produto> produtos = produtoService.ObterProdutoEanPorDescricao(descricao);
		if (produtos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

}
