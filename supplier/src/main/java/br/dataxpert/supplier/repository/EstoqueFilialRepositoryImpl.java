package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.EstoqueFilial;

@Repository
public class EstoqueFilialRepositoryImpl implements EstoqueFilialRepository {

	public List<EstoqueFilial> ObterEstoqueFilialPorEAN(String filial, String ean) {

		// Eliminar zero a esquerda
		Long eanNumber = Long.parseLong(ean);
		ean = eanNumber.toString();

		String sql = " SELECT DISTINCT PCEST.CODFILIAL, PCEST.CODPROD, PCPRODUT.DESCRICAO, NVL(PCTABPR.PVENDA, 0) PRECO ";
		sql += " , NVL(PCEST.QTESTGER, 0) - NVL(PCEST.QTRESERV, 0) - NVL(PCEST.QTBLOQUEADA, 0) - NVL(PCEST.QTPENDENTE, 0) ESTOQUE_DISPONIVEL  ";
		sql += " FROM PCEST, PCPRODUT, PCEMBALAGEM ";
		sql += " , (      ";
		sql += " SELECT PCREGIAO.NUMREGIAO ";
		sql += " , PCREGIAO.REGIAO ";
		sql += " , PCREGIAO.UF ";
		sql += " , PCFILIAL.CODIGO CODFILIAL ";
		sql += " , PCFILIAL.FANTASIA LOJA ";
		sql += " FROM PCREGIAO ";
		sql += " , ( ";
		sql += " SELECT PCPARAMFILIAL.CODFILIAL ";
		sql += " , PCPARAMFILIAL.VALOR NUMREGIAO ";
		sql += " FROM PCPARAMFILIAL ";
		sql += " WHERE 1 = 1 ";
		sql += " AND PCPARAMFILIAL.NOME = 'NUMREGIAOPADRAOVAREJO' ";
		sql += " ) PARAM ";
		sql += " , PCFILIAL ";
		sql += " WHERE 1 = 1 ";
		sql += " AND PCREGIAO.NUMREGIAO = PARAM.NUMREGIAO ";
		sql += " AND PARAM.CODFILIAL = PCFILIAL.CODIGO ";
		sql += " AND PCFILIAL.DTEXCLUSAO IS NULL ";
		sql += " AND NVL(PCREGIAO.STATUS, 'A') = 'A' ";
		sql += " ) REGIAO ";
		sql += " , PCTABPR ";
		sql += " WHERE 1 = 1 ";
		sql += " AND PCEST.CODPROD = PCPRODUT.CODPROD ";
		sql += " AND PCEST.CODFILIAL = REGIAO.CODFILIAL ";
		sql += " AND PCTABPR.NUMREGIAO = REGIAO.NUMREGIAO ";
		sql += " AND PCTABPR.CODPROD = PCEST.CODPROD ";
		sql += " AND ( ";
		sql += "      (PCPRODUT.CODAUXILIAR  = '" + ean + "'";
		sql += "       OR PCPRODUT.CODAUXILIAR2 = '" + ean + "' )";
		sql += "     OR ( PCEMBALAGEM.CODAUXILIAR IN ('" + ean + "') )";
		sql += "      ) "; // -- TRABALHAR COM EAN E DUN
		sql += " AND PCEMBALAGEM.CODPROD = PCPRODUT.CODPROD ";
		sql += " AND PCEST.CODFILIAL IN ( SELECT PCFILIAL.CODIGO FROM PCFILIAL ";
		sql += "                           WHERE PCFILIAL.UF IN ( SELECT PCFILIAL.UF FROM PCFILIAL WHERE PCFILIAL.CODIGO = "
				+ filial + " ) ) ";
		sql += " ORDER BY PCEST.CODFILIAL ";

		List<EstoqueFilial> results = new ArrayList<EstoqueFilial>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstoqueFilial item = new EstoqueFilial();
				String codfilial = resultSet.getString("CODFILIAL").toString();
				item.setFilial(codfilial);
				String codproduto = resultSet.getString("CODPROD").toString();
				item.setCodproduto(codproduto);
				String descproduto = resultSet.getString("DESCRICAO").toString();
				item.setDescproduto(descproduto);
				String estoque = resultSet.getString("ESTOQUE_DISPONIVEL").toString();
				item.setEstoque(estoque);
				String preco = resultSet.getString("PRECO").toString();
				item.setPreco(preco);
				item.setEan(ean);
				results.add(item);

			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return results;

	}
	

	public List<EstoqueFilial> ObterEstoquePorFornecedor(String cnpjfornecedor, String descricao) {

		descricao = descricao.toUpperCase();
		
		String sql = " Select pcprodut.codprod, pcprodut.descricao, pcprodut.codauxiliar, pcprodut.classevenda,";
		sql += " NVL(PCEST.QTESTGER, 0) ESTOQUE_DISPONIVEL, pcest.codfilial, ";
		sql += " round((pcest.qtgirodia * pcest.qtestger), 0) dias, (pcest.qtpedida) ";
		sql += " From pcest, pcprodut, pcfornec ";
		sql += " Where pcest.codprod = pcprodut.codprod ";
		sql += " And pcprodut.codfornec = pcfornec.codfornec ";
		sql += " And pcfornec.cgc = '" + cnpjfornecedor + "'";
		sql += " And pcprodut.descricao like '%" + descricao + "%'";
		sql += " UNION ";
		sql += " Select pcprodut.codprod, pcprodut.descricao, pcprodut.codauxiliar, pcprodut.classevenda,";
		sql += " NVL(PCEST.QTESTGER, 0) ESTOQUE_DISPONIVEL, pcest.codfilial, ";
		sql += " round((pcest.qtgirodia * pcest.qtestger), 0) dias, (pcest.qtpedida) ";
		sql += " From pcest, pcprodut, pcfornec, pcnfent, pcmov ";
		sql += " Where pcest.codprod = pcprodut.codprod ";
		sql += " And pcfornec.codfornec = pcnfent.codfornec ";
		sql += " And pcest.codprod = pcmov.codprod ";
		sql += " And pcmov.numnota = pcnfent.numnota ";
		sql += " And pcmov.codoper in ('E','EB') ";
		sql += " And pcfornec.cgc = '" + cnpjfornecedor + "'";
		sql += " And pcprodut.descricao like '%" + descricao + "%'";

		List<EstoqueFilial> results = new ArrayList<EstoqueFilial>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstoqueFilial item = new EstoqueFilial();
				String codfilial = resultSet.getString("CODFILIAL").toString();
				item.setFilial(codfilial);
				String codproduto = resultSet.getString("CODPROD").toString();
				item.setCodproduto(codproduto);
				String descproduto = resultSet.getString("DESCRICAO").toString();
				item.setDescproduto(descproduto);
				String estoque = resultSet.getString("ESTOQUE_DISPONIVEL").toString();
				item.setEstoque(estoque);
				String classevenda = resultSet.getString("CLASSEVENDA").toString();
				item.setClassevenda(classevenda);
				String ean = resultSet.getString("CODAUXILIAR").toString();
				item.setEan(ean);
				String dias = resultSet.getString("DIAS").toString();
				item.setDias(dias);
				String qtpedida = resultSet.getString("QTPEDIDA").toString();
				item.setQtpedida(qtpedida);
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
