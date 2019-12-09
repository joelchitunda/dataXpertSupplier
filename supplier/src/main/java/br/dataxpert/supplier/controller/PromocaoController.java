package br.dataxpert.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.model.PromocaoItem;
import br.dataxpert.supplier.service.PromocaoService;

@Controller
@RequestMapping("api/promocao")
public class PromocaoController {

	@Autowired
	private PromocaoService promocaoService;
	
	//TODO - @RequestBody
	@RequestMapping(value = "/SalvarPromocao", method = RequestMethod.POST)
	public ResponseEntity<String> SalvarPromocao(@RequestParam(value="promocao") Promocao promocao) {

		String result = promocaoService.SalvarPromocao(promocao);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}
	//TODO - @RequestBody
	@RequestMapping(value = "/SalvarPromocaoItens", method = RequestMethod.POST)
	public ResponseEntity<Boolean> SalvarPromocaoItens(@RequestParam(value="promocao") List<PromocaoItem> plist) {

		Boolean result = promocaoService.SalvarPromocaoItens(plist);

		if (!result) {
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND

		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}
	
	
	
}
