package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.dataxpert.supplier.model.InfoProduto;
import br.dataxpert.supplier.service.InfoProdutoService;

@Controller
@RequestMapping("api/infoproduto")
public class InfoProdutoController {

	// public static final Logger logger =
		// LoggerFactory.getLogger(ChatBotButtonController.class);

		@Autowired
		private InfoProdutoService infoProdutoService;

		@RequestMapping(value = "/ObterInfoProdutoPorEAN", method = RequestMethod.GET)
		public ResponseEntity<List<InfoProduto>> ObterNotaFiscalPorFornecedor(@RequestParam(value="filial") String filial,
				@RequestParam(value="ean") String ean) {

			List<InfoProduto> results = infoProdutoService.ObterInfoProdutoPorEAN(filial, ean);
			List<InfoProduto> infos = results; 

			if (infos.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You may decide to return HttpStatus.NOT_FOUND

			}

			return new ResponseEntity<List<InfoProduto>>(infos, HttpStatus.OK);

		}

	
}
