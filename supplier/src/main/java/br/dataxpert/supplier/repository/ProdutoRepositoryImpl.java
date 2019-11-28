package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.conexaoBD.Conexao;
import br.dataxpert.supplier.model.Produto;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

	/*
	 * static {
	 * 
	 * try {
	 * 
	 * Class.forName("org.postgresql.Driver");
	 * 
	 * } catch (ClassNotFoundException ex) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private Connection getConnection() throws SQLException {
	 * 
	 * return DriverManager.getConnection("jdbc:postgresql://localhost:5432/meb_db",
	 * "postgres", "ma220905");
	 * 
	 * }
	 * 
	 * private void closeConnection(Connection connection) {
	 * 
	 * if (connection == null)
	 * 
	 * return;
	 * 
	 * try {
	 * 
	 * connection.close();
	 * 
	 * } catch (SQLException ex) {
	 * 
	 * }
	 * 
	 * }
	 */

	public List<Produto> ObterProdutoEanPorDescricao(String descricao) {

		descricao = descricao.toUpperCase();

		String sql = "Select pcprodut.codprod, ";
		sql += " pcprodut.descricao descricao, pcprodut.classevenda , pcprodut.codauxiliar ";
		sql += " From pcprodut ";
		sql += " Where pcprodut.descricao like '%" + descricao + "%'";
		sql += " Order by descricao ";

		List<Produto> results = new ArrayList<Produto>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

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
				results.add(item);
			}
			
			results.forEach(x -> System.out.println(x));

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return results;

	}

	public List<Produto> ObterProdutoPorDescricao(String cnpjfornecedor, String descricao) {

		descricao = descricao.toUpperCase();

		Connection connection = null;

		String sql = "Select pcprodut.codprod, ";
		sql += " pcprodut.descricao descricao, pcprodut.classevenda , pcprodut.codauxiliar ";
		sql += " From pcmov, pcprodut, pcfornec ";
		sql += " Where pcmov.codoper = 'S' ";
		sql += " And pcmov.codprod = pcprodut.codprod ";
		sql += " And pcprodut.codfornec = pcfornec.codfornec ";
		sql += " And pcfornec.cgc = '" + cnpjfornecedor + "'";
		sql += " And pcprodut.descricao like '%" + descricao + "%'";
		sql += " UNION ";
		sql += "Select pcprodut.codprod, ";
		sql += " pcprodut.descricao descricao, pcprodut.classevenda , pcprodut.codauxiliar ";
		sql += " From pcmov, pcprodut, pcfornec, pcnfsaid ";
		sql += " Where pcmov.codoper = 'S' ";
		sql += " And pcmov.codprod = pcprodut.codprod ";
		sql += " And pcmov.numnota = pcnfsaid.numnota ";
		sql += " And pcfornec.codfornec = pcnfsaid.codfornec ";
		sql += " And pcfornec.cgc = '" + cnpjfornecedor + "'";
		sql += " And pcprodut.descricao like '%" + descricao + "%'";
		sql += " Order by descricao ";

		List<Produto> results = new ArrayList<Produto>();

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
				results.add(item);

			}

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);

		} finally {

			Conexao.close();

		}

		return results;
	};

}
