package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.VendaProduto;

@Repository
public class VendaProdutoRepositoryImpl implements VendaProdutoRepository {

	public List<VendaProduto> ObterVendaProdutoPorFornecedor(String cnpjfornecedor, String descricao, String ano) {

		descricao = descricao.toUpperCase();

		String sql = "Select pcmov.codfilial, extract(month from dtmov) mes, pcprodut.codprod, ";
		sql += " pcprodut.descricao, pcprodut.classevenda , sum(pcmov.qt) vendas ";
		sql += " From pcmov, pcprodut, pcfornec ";
		sql += " Where pcmov.codoper = 'S' ";
		sql += " And extract(year from dtmov) = " + ano;
		sql += " And pcmov.codprod = pcprodut.codprod ";
		sql += " And pcprodut.codfornec = pcfornec.codfornec ";
		sql += " And pcfornec.cgc = '" + cnpjfornecedor + "'";
		sql += " And pcprodut.descricao like '%" + descricao + "%'";
		sql += " Group by pcmov.codfilial, extract(month from dtmov), pcprodut.codprod, pcprodut.descricao, pcprodut.classevenda ";
		sql += " UNION ";
		sql += "Select pcmov.codfilial, extract(month from dtmov) mes, pcprodut.codprod, ";
		sql += " pcprodut.descricao, pcprodut.classevenda , sum(pcmov.qt) vendas ";
		sql += " From pcmov, pcprodut, pcfornec, pcnfsaid ";
		sql += " Where pcmov.codoper = 'S' ";
		sql += " And extract(year from dtmov) = " + ano;
		sql += " And pcmov.codprod = pcprodut.codprod ";
		sql += " And pcmov.numnota = pcnfsaid.numnota ";
		sql += " And pcfornec.codfornec = pcnfsaid.codfornec ";
		sql += " And pcfornec.cgc = '" + cnpjfornecedor + "'";
		sql += " And pcprodut.descricao like '%" + descricao + "%'";
		sql += " Group by pcmov.codfilial, extract(month from dtmov), pcprodut.codprod, pcprodut.descricao, pcprodut.classevenda ";
		sql += " Order by mes ";

		List<VendaProduto> results = new ArrayList<VendaProduto>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				VendaProduto item = new VendaProduto();
				String filial = resultSet.getString("CODFILIAL").toString();
				item.setFilial(filial);
				String mes = resultSet.getString("MES").toString();
				item.setMes(mes);
				String codproduto = resultSet.getString("CODPROD").toString();
				item.setCodproduto(codproduto);
				String descproduto = resultSet.getString("DESCRICAO").toString();
				item.setDescproduto(descproduto);
				String vendas = resultSet.getString("VENDAS").toString();
				item.setVendas(vendas);
				String classevenda = resultSet.getString("CLASSEVENDA").toString();
				item.setClassevenda(classevenda);
				results.add(item);

			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return results;

	}

}
