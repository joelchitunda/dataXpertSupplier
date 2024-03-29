package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.TituloAPagar;

@Repository
public class TituloAPagarRepositoryImpl implements TituloAPagarRepository {

	public List<TituloAPagar> ObterTituloAPagarPorFornecedor(String cnpj, String ano) {

		String sql = " Select pclanc.recnum, pclanc.dtlanc, pclanc.historico, pclanc.numnota, ";
		sql += " pclanc.duplic, pclanc.valor, pclanc.dtvenc, pclanc.dtpagto,  ";
		sql += " pclanc.codfilial, pclanc.formapgto, pclanc.numtransentnf ";
		sql += " From pclanc, pcfornec ";
		sql += " Where pclanc.codfornec = pcfornec.codfornec  ";
		sql += " and pcfornec.cgc = '" + cnpj + "'";
		sql += " and extract(year from pclanc.dtvenc) = " + ano;
		sql += " and pclanc.numtransentnf > 0 ";
		sql += " Order by pclanc.dtvenc desc  ";

		List<TituloAPagar> results = new ArrayList<TituloAPagar>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TituloAPagar item = new TituloAPagar();
				String numero = resultSet.getString("RECNUM").toString();
				item.setNumero(numero);
				String dtlanc = resultSet.getString("DTLANC").toString();
				item.setDtlanc(dtlanc);
				String historico = resultSet.getString("HISTORICO").toString();
				item.setHistorico(historico);
				String numnota = resultSet.getString("NUMNOTA").toString();
				item.setNumnota(numnota);
				String duplicata = resultSet.getString("DUPLIC").toString();
				item.setDuplicata(duplicata);
				String valor = resultSet.getString("VALOR").toString();
				item.setValor(valor);
				String dtvenc = resultSet.getString("DTVENC").toString();
				item.setDtvenc(dtvenc);
				String dtpagto = resultSet.getString("DTPAGTO").toString();
				item.setDtpagto(dtpagto);
				String filial = resultSet.getString("CODFILIAL").toString();
				item.setFilial(filial);
				String formapagto = resultSet.getString("FORMAPGTO").toString();
				item.setFormapagto(formapagto);
				String numtransacao = resultSet.getString("NUMTRANSENTNF").toString();
				item.setNumtransacao(numtransacao);
				results.add(item);

			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return results;

	}

	public List<TituloAPagar> ObterTotalAPagarPorFornecedor(String cnpj, String ano) {

		String sql = " Select extract(month from pclanc.dtlanc) MES, sum(pclanc.valor) VALOR ";
		sql += " From pclanc, pcfornec ";
		sql += " Where pclanc.codfornec = pcfornec.codfornec  ";
		sql += " and pcfornec.cgc = '" + cnpj + "'";
		sql += " and extract(year from pclanc.dtvenc) = " + ano;
		sql += " and pclanc.numtransentnf > 0 ";
		sql += " Group by extract(month from pclanc.dtlanc)  ";
		sql += " Order by extract(month from pclanc.dtlanc)  ";

		List<TituloAPagar> results = new ArrayList<TituloAPagar>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TituloAPagar item = new TituloAPagar();
				String dtlanc = resultSet.getString("MES").toString();
				item.setDtlanc(dtlanc);
				String valor = resultSet.getString("VALOR").toString();
				item.setValor(valor);
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
