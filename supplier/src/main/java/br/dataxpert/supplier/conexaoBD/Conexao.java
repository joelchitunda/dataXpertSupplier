package br.dataxpert.supplier.conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection conexao = null;

	public static void close() {// fecha a conexao

		try {

			conexao.close();

		} catch (SQLException ex) {

		}

	}

	public void commit()  {// "comitta" a conexao SUPER CUIDADO AO USAR

		try {
			
			conexao.commit();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void rollback()  {// "crt+z" da conexao, caso queira voltar antes do commit/

		try {
			conexao.rollback();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public static Connection getConexao() {// cria uma conexão com o banco

		if (conexao != null) {

			return conexao;

		} else {

			try {

				String driver = "oracle.jdbc.OracleDriver";// para rodar em bancos oracle ->
				// String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR";// localização do banco ->
				// String url = "jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR ";
				String user = "WINTHOR";
				String password = "WINTHOR";

				Class.forName(driver);
				conexao = DriverManager.getConnection(url, user, password);

			//conexao = DriverManager.getConnection( user, password,"jdbc:oracle:thin@10.10.0.15:1521:WINTHOR");//url);

			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return conexao;
		}
	}
}
