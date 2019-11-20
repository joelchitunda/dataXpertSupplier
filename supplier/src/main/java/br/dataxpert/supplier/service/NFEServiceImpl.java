package br.dataxpert.supplier.service;

import java.sql.Connection;

import org.springframework.stereotype.Service;

import br.dataxpert.supplier.repository.NFERepository;

@Service
public class NFEServiceImpl implements NFEService {

	public NFERepository repository;
	

	public String RegistrarEntradaNFFilial(String filial, String chavenfe, String usuario, String data) {

		return repository.RegistrarEntradaNFFilial(filial, chavenfe, usuario, data);

	}

	public String ModificarSituacaoNFe(String chavenfe, int status, Connection conn) {

		return repository.ModificarSituacaoNFe(chavenfe, status, conn);

	}

}
