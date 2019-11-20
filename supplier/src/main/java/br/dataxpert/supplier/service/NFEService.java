package br.dataxpert.supplier.service;

import java.sql.Connection;

public interface NFEService {

	String RegistrarEntradaNFFilial(String filial, String chavenfe, String usuario, String data);
	
	String ModificarSituacaoNFe(String chavenfe, int status, Connection conn);

}
