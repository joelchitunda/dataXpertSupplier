package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class NFERepositoryImpl implements NFERepository {

	public String RegistrarEntradaNFFilial(String filial, String chavenfe, String usuario, String data) {

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

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.executeQuery();
			ret = "true";

			if (ret == "true") {

				ModificarSituacaoNFe(chavenfe, 2, conn);

			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			ret = "Erro -" + err.getMessage();

		}

		return ret;

	}

	
	public String ModificarSituacaoNFe(String chavenfe, int status, Connection connection) {

		String sql = " UPDATE MDC_NFE   ";
		sql += " SET STATUS = " + String.valueOf(status);
		sql += " WHERE CHAVENFE = " + "'" + chavenfe + "'";

		String ret = "NOK";

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.executeQuery();
			ret = "true";

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			ret = "Erro -" + err.getMessage();

		}

		return ret;
		
	}

}
