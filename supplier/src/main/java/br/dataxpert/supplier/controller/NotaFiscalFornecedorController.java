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

import br.dataxpert.supplier.model.NotaFiscalFornecedor;
import br.dataxpert.supplier.service.NotaFiscalFornecedorService;

@Controller
@RequestMapping("api/notafiscalfornecedor")
public class NotaFiscalFornecedorController {

	// public static final Logger logger =
	// LoggerFactory.getLogger(ChatBotButtonController.class);

	@Autowired
	private NotaFiscalFornecedorService notaFiscalFornecedorService;

	@RequestMapping(value = "/ObterNotaFiscalPorFornecedor", method = RequestMethod.GET)
	public ResponseEntity<List<NotaFiscalFornecedor>> ObterNotaFiscalPorFornecedor(@RequestParam(value="cnpj") String cnpj,
			@RequestParam(value="ano") String ano) {

		List<NotaFiscalFornecedor> notas = notaFiscalFornecedorService.ObterNotaFiscalPorFornecedor(cnpj, ano);

		if (notas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<List<NotaFiscalFornecedor>>(notas, HttpStatus.OK);

	}

}
