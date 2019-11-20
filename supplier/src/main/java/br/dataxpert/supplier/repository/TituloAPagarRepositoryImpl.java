package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.conexaoBD.Conexao;
import br.dataxpert.supplier.model.TituloAPagar;

@Repository
public class TituloAPagarRepositoryImpl implements TituloAPagarRepository {

	public List<TituloAPagar> ObterTituloAPagarPorFornecedor(String cnpj, String ano) {
		
		Connection connection = null;
		
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

		try {

			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

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

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);

		} finally {

			Conexao.close();

		}

		return results;

	}

	public List<TituloAPagar> ObterTotalAPagarPorFornecedor(String cnpj, String ano) {

		Connection connection = null;

		
		String sql = " Select extract(month from pclanc.dtlanc) MES, sum(pclanc.valor) VALOR ";
        sql += " From pclanc, pcfornec ";
        sql += " Where pclanc.codfornec = pcfornec.codfornec  ";
        sql += " and pcfornec.cgc = '" + cnpj + "'";
        sql += " and extract(year from pclanc.dtvenc) = " + ano;
        sql += " and pclanc.numtransentnf > 0 ";
        sql += " Group by extract(month from pclanc.dtlanc)  ";
        sql += " Order by extract(month from pclanc.dtlanc)  ";

		List<TituloAPagar> results = new ArrayList<TituloAPagar>();

		try {

			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				TituloAPagar item = new TituloAPagar();
				String dtlanc = resultSet.getString("MES").toString();
				item.setDtlanc(dtlanc);
				String valor = resultSet.getString("VALOR").toString();
				item.setValor(valor);
				results.add(item);

			}

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);

		} finally {

			Conexao.close();

		}

		return results;

	}

}
