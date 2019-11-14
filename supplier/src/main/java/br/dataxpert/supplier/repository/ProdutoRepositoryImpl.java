package br.dataxpert.supplier.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.conexaoBD.Conexao;
import br.dataxpert.supplier.model.Produto;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

	/*static {

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException ex) {

		}

	}

	private Connection getConnection() throws SQLException {

		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/meb_db", "postgres", "ma220905");

	}

	private void closeConnection(Connection connection) {

		if (connection == null)

			return;

		try {

			connection.close();

		} catch (SQLException ex) {

		}
		
	}*/

	public List<Produto> ObterProdutoEanPorDescricao(String descricao) {

		Connection connection = null;

		String sql = "Select pcprodut.codprod, ";
		sql += " pcprodut.descricao descricao, pcprodut.classevenda , pcprodut.codauxiliar ";
		sql += " From pcprodut ";
		sql += " Where pcprodut.descricao like '%" + descricao + "%'";
		sql += " Order by descricao ";

		List<Produto> result = new ArrayList<Produto>();
		
		try {
			
			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				
				Produto item = new Produto();
				String codproduto = resultSet.getString("CODPROD").toString();
				item.setCodproduto(codproduto);
				String classevenda = resultSet.getString("CLASSEVENDA").toString();
				item.setClassevenda(classevenda);
				String descproduto = resultSet.getString("DESCRICAO").toString();
				item.setDescproduto(descproduto);
				String ean = resultSet.getString("CODAUXILIAR").toString();
				item.setEan(ean);
				result.add(item);
				
			}
			
		} catch (Exception err) {
			
			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			
		} finally {
			
			Conexao.close();
			
		}
		
		return result;

	};

}
