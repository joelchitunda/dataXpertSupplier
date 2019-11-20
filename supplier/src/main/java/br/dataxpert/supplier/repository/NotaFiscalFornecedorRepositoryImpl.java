package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.conexaoBD.Conexao;
import br.dataxpert.supplier.model.NotaFiscalFornecedor;

@Repository
public class NotaFiscalFornecedorRepositoryImpl implements NotaFiscalFornecedorRepository {

	public List<NotaFiscalFornecedor> ObterNotaFiscalPorFornecedor(String cnpj, String ano) {

		Connection connection = null;

		String sql = " select numnota, dtemissao, vltotal, totpeso, dtsaida, codfilial, uf, chavenfe, ";
		sql += " cgcfilial, uffilial   , prazo ";
		sql += " from  pcnfent ";
		sql += " where cgc = '" + cnpj + "'";
		sql += " and extract(year from dtemissao) = " + ano.toString();
		sql += " order by dtemissao desc ";

		List<NotaFiscalFornecedor> results = new ArrayList<NotaFiscalFornecedor>();

		try {

			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				NotaFiscalFornecedor item = new NotaFiscalFornecedor();
				String numeronota = resultSet.getString("NUMNOTA").toString();
				item.setNumeronota(numeronota);
				String dataemissao = resultSet.getString("DTEMISSAO").toString();
				item.setDataemissao(dataemissao);
				String valornota = resultSet.getString("VLTOTAL").toString();
				item.setValornota(valornota);
				String peso = resultSet.getString("TOTPESO").toString();
				item.setPeso(peso);
				String filial = resultSet.getString("CODFILIAL").toString();
				item.setFilial(filial);
				String uf = resultSet.getString("UF").toString();
				item.setUf(uf);
				String chavenfe = resultSet.getString("CHAVENFE").toString();
				item.setChavenfe(chavenfe);
				String cnpjfilial = resultSet.getString("CGCFILIAL").toString();
				item.setCnpjfilial(cnpjfilial);
				String prazo = resultSet.getString("PRAZO").toString();
				item.setPrazo(prazo);
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
