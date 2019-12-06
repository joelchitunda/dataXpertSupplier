package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.ItemPedidoFilial;
import br.dataxpert.supplier.model.PedidoFilial;
import br.dataxpert.supplier.model.ResumoPedidoFornecedor;

@Repository
public class PedidoFilialRepositoryImpl implements PedidoFilialRepository {

	public List<PedidoFilial> ObterPedidoFilialPorFornecedor(String cnpj, String ano, String mes) {

		String sql = " Select distinct pcpedido.numped NUMPED, pcpedido.rotinalanc PEDLANC, ";
		sql += " pcpedido.dtemissao EMIPED, pcpedido.vltotal VLPED, pcpedido.codfilial FILIALPED,  ";
		sql += " pcpedido.dtprevent PREVPED, pcmov.numnota NUMNOTA, pcnfent.vltotal VLNOTA, ";
		sql += " pcnfent.dtemissao EMINOTA, mdc.dataentrada RECEPNOTA ";
		sql += " From pcpedido, pcmov, pcnfent, pcfornec, mdc_entradanfefilial mdc  ";
		sql += " Where pcpedido.codfornec = pcfornec.codfornec  ";
		sql += " and pcfornec.cgc = '" + cnpj + "'";
		sql += " and pcpedido.codfilial = pcmov.codfilial  ";
		sql += " and pcmov.codoper in ('E', 'EB', 'ER')  ";
		sql += " and pcpedido.numped = pcmov.numped(+)  ";
		sql += " and extract(year from pcpedido.dtemissao) = " + ano;
		sql += " and extract(month from pcpedido.dtemissao) = " + mes;
		sql += " and pcmov.numnota = pcnfent.numnota  ";
		sql += " and pcmov.codfilial = pcnfent.codfilial  ";
		sql += " and pcnfent.chavenfe = mdc.chavenfe(+)  ";
		sql += " Order by pcpedido.dtemissao desc  ";

		List<PedidoFilial> results = new ArrayList<PedidoFilial>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				PedidoFilial item = new PedidoFilial();
				String numeropedido = resultSet.getString("NUMPED").toString();
				item.setNumeropedido(numeropedido);
				String rotinalancamento = resultSet.getString("PEDLANC").toString();
				item.setRotinalancamento(rotinalancamento);
				String emissao = resultSet.getString("EMIPED").toString();
				item.setEmissao(emissao);
				String valorpedido = resultSet.getString("VLPED").toString();
				item.setValorpedido(valorpedido);
				String filial = resultSet.getString("FILIALPED").toString();
				item.setFilial(filial);
				String previsaoentrega = resultSet.getString("PREVPED").toString();
				item.setPrevisaoentrega(previsaoentrega);
				String numeronota = resultSet.getString("NUMNOTA").toString();
				item.setNumeronota(numeronota);
				String valornota = resultSet.getString("VLNOTA").toString();
				item.setValornota(valornota);
				String emissaonota = resultSet.getString("EMINOTA").toString();
				item.setEmissaonota(emissaonota);
				String recepcaonota = resultSet.getString("RECEPNOTA").toString();
				item.setRecepcaonota(recepcaonota);
				results.add(item);

			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return results;

	}

	public List<ItemPedidoFilial> ObterItemPedidoFilialPorPedido(String filial, String pedido) {

		String sql = " select pcitem.numped, pcprodut.codprod, pcprodut.descricao, pcprodut.qtunitcx, ";
		sql += " pcprodut.unidade ,pcitem.qtpedida, pcitem.pcompra, pcitem.qtentregue, ";
		sql += " pcprodut.obs ";
		sql += " from pcitem, pcprodut ";
		sql += " where pcitem.numped = " + pedido;
		sql += " and pcitem.codprod = pcprodut.codprod ";
		sql += " order by pcprodut.descricao ";

		List<ItemPedidoFilial> results = new ArrayList<ItemPedidoFilial>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ItemPedidoFilial item = new ItemPedidoFilial();
				String numeropedido = resultSet.getString("NUMPED").toString();
				item.setNumeropedido(numeropedido);
				String codproduto = resultSet.getString("CODPROD").toString();
				item.setCodproduto(codproduto);
				String descproduto = resultSet.getString("DESCRICAO").toString();
				item.setDescproduto(descproduto);
				item.setFilial(filial);
				String unidade = resultSet.getString("UNIDADE").toString();
				item.setUnidade(unidade);
				String qtdepedida = resultSet.getString("QTPEDIDA").toString();
				item.setQtdepedida(qtdepedida);
				String qtdeentregue = resultSet.getString("QTENTREGUE").toString();
				item.setQtdeentregue(qtdeentregue);
				String valorunitario = resultSet.getString("PCOMPRA").toString();
				item.setValorunitario(valorunitario);
				String multiplo = resultSet.getString("QTUNITCX").toString();
				item.setMultiplo(multiplo);
				String obs = resultSet.getString("OBS").toString();
				item.setObs(obs);
				results.add(item);

			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return results;

	}

	public List<ResumoPedidoFornecedor> ObterResumoPedidosFornecedor(String cnpj) {

		String sql = " Select extract(year from pcpedido.dtemissao) ano, extract(month from pcpedido.dtemissao) mes, ";
		sql += " count(pcpedido.numped) QTPED,  ";
		sql += " sum(pcpedido.vltotal) VLRPED ";
		sql += " From pcpedido, pcfornec ";
		sql += " Where pcpedido.codfornec = pcfornec.codfornec ";
		sql += " and pcfornec.cgc = '" + cnpj + "'";
		sql += " group by extract(year from pcpedido.dtemissao), extract(month from pcpedido.dtemissao) ";
		sql += " order by ano, mes desc ";

		List<ResumoPedidoFornecedor> results = new ArrayList<ResumoPedidoFornecedor>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ResumoPedidoFornecedor item = new ResumoPedidoFornecedor();
				String ano = resultSet.getString("ANO").toString();
				item.setAno(ano);
				String mes = resultSet.getString("MES").toString();
				item.setMes(mes);
				String qtde = resultSet.getString("QTPED").toString();
				item.setQtde(qtde);
				String valorpedido = resultSet.getString("VLRPED").toString();
				item.setValorpedido(valorpedido);
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
