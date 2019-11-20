package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.conexaoBD.Conexao;

@Repository
public class NFERepositoryImpl implements NFERepository {

	
	public String RegistrarEntradaNFFilial(String filial, String chavenfe, String usuario, String data) {

		Connection connection = null;

		String sql = " INSERT INTO MDC_ENTRADANFEFILIAL   ";
		sql += " (CODFILIAL, CHAVENFE, DATAENTRADA, USUARIO, REGISTRO ) ";
		sql += " VALUES ( ";
		sql += filial + ", ";
		sql += "'" + chavenfe + "',";
		sql += "TO_DATE('" + data + "','DD-MM-YYYY HH24:MI:SS'),";
		sql += usuario + ",";
		sql += "SYSDATE ";
		sql += " )";

		String ret = "NOK";

		try {

			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeQuery();
			ret = "true";

			if (ret == "true") {

				ModificarSituacaoNFe(chavenfe, 2, connection);

			}

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			ret = "Erro -" + err.getMessage();

		} finally {

			Conexao.close();

		}

		return ret;
		
	}

	public String ModificarSituacaoNFe(String chavenfe, int status, Connection conn) {
		
		Connection connection = null;

		String sql = " UPDATE MDC_NFE   ";
        sql += " SET STATUS = " + String.valueOf(status);
        sql += " WHERE CHAVENFE = " + "'" + chavenfe + "'";

		String ret = "NOK";

		try {

			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeQuery();
			ret = "true";


		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			ret = "Erro -" + err.getMessage();

		} finally {

			Conexao.close();

		}

		return ret;
	}

}
