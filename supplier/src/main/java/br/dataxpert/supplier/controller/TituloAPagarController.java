package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.dataxpert.supplier.model.TituloAPagar;
import br.dataxpert.supplier.service.TituloAPagarService;

@Controller
@RequestMapping("api/tituloapagar")
public class TituloAPagarController {
	// public static final Logger logger =
	// LoggerFactory.getLogger(ChatBotButtonController.class);

	@Autowired
	private TituloAPagarService tituloAPagarService;

	@RequestMapping(value = "/ObterTituloAPagarPorFornecedor", method = RequestMethod.GET)
	public ResponseEntity<List<TituloAPagar>> ObterTituloAPagarPorFornecedor(@RequestParam(value = "cnpj") String cnpj,
			@RequestParam(value = "ano") String ano) {

		List<TituloAPagar> results = tituloAPagarService.ObterTituloAPagarPorFornecedor(cnpj, ano);
		List<TituloAPagar> titulos = results;

		if (titulos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<TituloAPagar>>(titulos, HttpStatus.OK);

	}

	@RequestMapping(value = "/ObterTotalAPagarPorFornecedor", method = RequestMethod.GET)
	public ResponseEntity<List<TituloAPagar>> ObterTotalAPagarPorFornecedor(@RequestParam(value = "cnpj") String cnpj,
			@RequestParam(value = "ano") String ano) {

		List<TituloAPagar> results = tituloAPagarService.ObterTotalAPagarPorFornecedor(cnpj, ano);
		List<TituloAPagar> titulos = results;

		if (titulos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<TituloAPagar>>(titulos, HttpStatus.OK);

	}

}
