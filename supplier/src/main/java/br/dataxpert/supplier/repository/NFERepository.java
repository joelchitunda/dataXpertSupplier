package br.dataxpert.supplier.repository;

import java.sql.Connection;

public interface NFERepository {

	String RegistrarEntradaNFFilial(String filial, String chavenfe, String usuario, String data);
	
	String ModificarSituacaoNFe(String chavenfe, int status, Connection conn);

}
